Leadership Principles 领导力原则
1.Customer Obesssion						强调用户体验 围绕用户需求进行设计
2.Ownership									有责任心 对自己的project当作自己的career来做 而不仅仅是一份工作
3.Invent and Simplify						不拘泥于现有的技术 愿意发明新算法来解决新的问题
4.Are Right, A Lot 							客观全面分析 对不确定的问题有vision  They have strong judgment and good instincts. They seek diverse perspectives and work to disconfirm their beliefs.		
5.Learn and Be Curious
6.Hire and Develop The Best
7.Insist on the Highest Standards			追求完美
8.Think Big									有大局观
9.Bias for Action 							及时行动 
10.Frugality 								不盲目增加成本
11.Earn Trust								愿意听同事们的意见
12.Dive Deep 								深入研究 深潜
13.Have Backbone, Disagree and Commit		有不同的意见要坚决的表现出来 但是 群体意见形成后 还是要 commit wholey(全力以赴) 
14.Deliver Results. 						及时deliver结果 respect deadline

1.requirement排在第一，deadline第二。
2.有manager出现的选项无脑选manager，manager就是一个组的地头蛇。

Amazon9条主要原则
原则1：客户是上帝，requirement优先，任何影响上帝的事情都不能干，
        如某个requirement影响了上帝的体验，
        你就是死键盘上也不能砍了，宁愿miss deadline
原则2：为长远考虑，即客户几年之后可能会出现的需求也要考虑到，
        不会为了交付短期的deadline，
        而牺牲长期的价值。（比如 global api  和 local api）
原则3：最高标准，“最高”对应上面的“长远”。
原则4：一般情况，能请示"manager"就请示manager，manager一般不会出错
原则5：速度很重要，决策和行动都可以改变，因此不需要进行过于广泛的推敲
        ，但提倡在深思熟虑下进行冒险。
原则6：不需要一定要坚持“非我发明”，需求帮助也是可以的，四处寻找创意
        ，并且接受长期被误导的可能
原则7：敢于承担责任，任劳任怨，比如领导说谁会java，你会你就跳出来说我会
原则8：对问题刨根问底，探究细节
原则9：服从大局（团队比个人重要）






题型

	（1.区分效能）对于某个问题，某个情况，你有五种不同的态度	（大部分题的一个框可以放多个，开头介绍会说哪种框可以放多个，那种只能放一个）
				most effective 
				least effective
	（2.选择最佳）从多个选择中，选择你认为最好的。多选一		










Work Simulation 这一部分 有很多时间 充分思考 要看清题 看它考的是 哪一个leadership

客户 Customer > Deadline > Anything else

1）deadline 是最重要的（说三遍），有用户需求的时候requirement更重要。
2）多跟 manager/VP/senior/other team/colleague 交流，不能自己暗搓搓做。
3）自己要主动多帮忙。





--------------------------------------------------------
1. code view 被说垃圾怎么办
	P1 有个同事说你做的很烂建议重来，忘记过程了总之是和他约 meeting
	P2 meeting 的时候听他 bb 了一通直到下班，记了笔记但还有没懂的怎么办
		排序
		
		3）大哥再聊两块钱的呗
		6）team meeting 一起学（看啊这就是我不会写的东西，这下你们知道我水平了吧）
		5）喊路人甲帮忙一起看
		4）回去自己研究研究，不明白的明天再来问  
		1）照着比较写，能写啥样写啥样
		2）WRNG 这么麻烦，老子不写了，还是原来的方法吧 
	


------------------------------------------------------------------------------------
找bug 一些顾客无法查看网站上某些商品

	1. 区分效能 排序 哪些方法找bug快
		log 
		screenshot of error page
		user id 
		user area
		browser version
	2. 给了"log"让选bug是啥
		应该是 "review" 和 "rating" unequal

		代码不work的地方是json最后俩object的内容长度不一致
		就是 用户review 和 打分个数 不匹配



------------------------------------------------------------------------------------
两个Delivery Algorithm 选一个，根据提供的100天数据统计表。
	每天每车送件 >= 80，里程<= 200，在 100 天的数据上 develop，3 天 test

	P1 给了两个 algorithm 的结果，一个是送的多但跑的久，另一个跑的少但没送够，让你选哪个好
		排序： 四个理由，有两个分别支持 1，2 的 based on delivery/mileage，还有几个都是没提到的 feature，什么平均里程少 balabala 的

	P2 双选： 1）城市地图 "2）algo 在哪 100 天里的表现 3）明确delivery 和 mileage到底哪个更重要" 4）司机的表现 5）对之后影响 algo 的因素的预测

	多要点数据放在了首位

	P3 给了develop time 和 test time 的信息
		发现 develop time 基本不怎么堵，test 的时候城里很堵，问这下怎么选，希望三天内上线
		单选：" 1）algo1，因为 delivery "
			  2）我母鸡 
			  3）algo2，因为 mileage 的波动来自于 traffic （可是我看 algo2 的 mileage 也没问题啊）
			  4）algo2，因为 mileage 
			  5）我全都不要，要不我们搞个新的吧

------------------------------------------------------------------------------------
跟 external team 合作搞 两个feature选一

	fearure1 : 是搞一个第三方服务的新系统, 目前只在 social media A 和 B 上做了, 想 generalize 要再写

	1.feature1的4个优点 	1.media B 很多年轻用户，增长很快 2. 和 B 合作有钱挣
	2.feature2的2个优点 	1.解决 crashes   2.external team 感兴趣，不用和其他 team 协调，做起来快
	3. P1 选优先哪个feature。

		按照地里面经我选了"feature1"，然后一个女的说看起来你像是个新手，我们应该选2问你怎么办（区分效能，如何沟通）
		P2 找 manager 找个 senior 约个 meeting 小组投票 向大佬低头 去您*的吧
 
	4.最终大boss还是选feature1 
		队友有个代码没有test想直接publish。
		我们作为责任心极强的准亚麻人。那必须不能让他得逞呀！好说歹说他还是悄咪咪上了。结果就bug了。
		"咨询manager，senior，组织开会"。全给他整上。

		最后一题我选了"设计一个自动化程序"阻止没有test就上传代码的行为

------------------------------------------------------------------------------------
有个 feature 出问题了manager 让你改，说三天改好， P说时间很紧今天上线，要不就不 test 了
	P1 排序
	 1）和 PM，manager 三方会谈 
	 5）要不 PM和 manager你俩先打一架
	 4）找 senior（大师兄救我）
	 2）没有 test 老子绝不放手 
	 3）那就不test 了反正没多大点事






------------------------------------------------------------------------------------
三个同事（ABC）AB说good job。C说 你用错design pattern类似的。怎么沟通。
	【开会—开会—交流】

















	




		

























































