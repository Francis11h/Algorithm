List<CartDTO> cartDTOList = orderDTO.getOrderDetailList()
									.stream()
									.map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
									.collect(Collectors.toList());

这两段 是等价的

List<CartDTO> cartDTOList = new ArrayList<>();

for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
	//...
	CartDTO cartDTO = new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity());
    cartDTOList.add(cartDTO);
}


第一步 集合变为流
	Stream 流 是Java SE 8 类库中新增的关键抽象，它被定义于 java.util.stream

		集合和流尽管在表面上看起来很相似，但它们的设计目标是不同的：
		集合主要用来对其元素进行有效（effective）的管理和访问（access）
		而流并不支持对其元素进行直接操作或直接访问，而只支持通过声明式操作在其上进行运算然后得到结果

第二步 map 映射
	'e' 代表的是 流中的每个元素
	将客户端传来的商品id和数量组成list

第三步 collect
	collect 会 把其接收到的元素 聚集在一起 collect() 里面的参数 被用来指定如何进行聚集操作
	Collectors.toList() 就表示 每个被收集的元素 加入List中









