整体描述 Overall 

Its a full-stack webApp, which is an ordering system.
The backend and frontend are isloated by the Nginx 前端 and Tomcat 后端.    

前后端分离的核心思想
the Html pages of the front-end call the Restful-api developed by the back-end using ajax, 
and communication through Json


The back-end was developed by SpringBoot 
The web page of the back-end was builted by using the combination of Bootstrap + Freemarker + Jquery.
And the DB is based on SpringBoot JPA and a few of MyBatis.
The caching involves Redis‘s caching mechanism (Distributed-Session, Distributed-Lock)
Also use Websocket for message push 

And it is a WeChat based take-out service system, so the technology that related with weChat platform is necessary,
such as Scan code login + Template message push + WeChat Payment and Refund.




环境 
virtualBox and Linux centos 7.3




DB 设计

product_category 

product_info

order_detail

order_table




完全是按照 工业界的要求来设计的 webApp
学习了一套 工业界设计的模式 与 结构 
SpringBoot 架构 Entity Repository Service ServiceImpl Controller

充分理解了 面向接口编程 

接口继承接口 实现对方法的扩展


RESTful风格的接口返回的使用

每写一个 DAO Service 都做 Unit test


数据组装成前端需要的样子（VO）：类中嵌套各种数据结构
数据转换成前端需要的样子（标签）：date->long并且去掉最后三位进行加工，标签使用
如果从前台接收的数据与后台数据不能保持一致，可以设计DTO包（数据传输对象（DTO)(Data Transfer Object)）




project的难点 

orderServiceImplementation

Distributed-Lock


http://www.redis.cn/commands/setnx.html

SETNX key value	
将key设置值为value
	如果key不存在, 这种情况下等同SET命令
	当key存在时, 什么也不做.

设计模式: 使用!SETNX加锁

算法只需具备3个特性就可以实现一个最低保障的分布式锁。

安全属性（Safety property）: 独享（相互排斥） 	在任意一个时刻，只有一个客户端持有锁。
活性A(Liveness property A): 无死锁			即便持有锁的客户端崩溃（crashed)或者网络被分裂（gets partitioned)，锁仍然可以被获取。
活性B(Liveness property B): 容错				只要大部分Redis节点都活着，客户端就可以获取和释放锁.


GETSET key value 
自动将key对应到value并且 返回 原来key对应的value



public class RedisLock {
    @Autowired
    private StringRedisTemplate redisTemplate;
	/**
	 *  加锁
	 * @param key   productId
	 * @param value (当前时间 + 超时时间TimeOut) 时间戳格式
	 * @return
	 */

	public boolean lock (String key, String value) {
	    // 如果可以成功设置 即 key之前不存在 即没人占有这个lock 返回true 代表成功拿到锁
	    // setIfAbsent 等同于 SETNX 这个 redis 命令
	    if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
	        return true;
	    }
	    // 加的这一串而 会避免死锁 : 因为下面的 万一数据库 IO异常 上面的没法写 永远返回fasle 就是 deadlock
	    // 同时保证 多个线程同时进来 只有一个会拿到锁 因为 假设currentValue = A 两个线程的value都是B 其中一个走了getAndSet 会修改value 另一个拿到的oldValue就不会是A 而是B了
	    String currentValue = redisTemplate.opsForValue().get(key);
	    // 如果锁过期(即 1.锁被占用 并且 2.之前输入的时间 + timeout < 当前时间)
	    if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()) {
	        // 获取上一个锁的时间 并且设置新的value进去
	        // getAndSet 等同于 redis中GETSET方法
	        String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
	        if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {
	            return true;
	        }
	    }
	    // 没过期的话 直接返回false
	    return false;
	}

	/**
	 * 解锁 其实就是删掉key
	 * @param key
	 * @param value
	 */

	public void unlock (String key, String value) {
	    try {
	        String currentValue = redisTemplate.opsForValue().get(key);
	        if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
	            redisTemplate.opsForValue().getOperations().delete(key);
	        }
	    } catch (Exception e) {
	        log.error("【redis分布式锁】 解锁异常");
	    }
	}
}



用锁的 秒杀程序 
public void orderProductMockDiffUser(String productId) {
	//加锁
	long time = System.currentTimeMillis() + TIMEOUT;
	if (!redisLock.lock(productId, String.valueOf(time))) {
		throw new Exception(...);
	}

	// 操作数据库网络 I/O  有可能会抛出 exception 上面已经处理了deadLock
	//1. 查询该商品库存 
	//2. 下单
	//3. 减库存

	//解锁
	redisLock.unlock(productId, String.valueOf(time));
}




