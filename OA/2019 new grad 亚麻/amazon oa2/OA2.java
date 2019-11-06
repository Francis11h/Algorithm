Subtree of Another Tree

Two Sum - Unique Pairs

Book genre		/ 	Favorite Genres
	有同学会有runtime error 我check了两个条件 1. genre里有book 而不是empty list或者null 2. book有genre 然后就全过了

Critical connections
		 1. index是从1开始的 2. 我把PairInt加进result list里的时候 确保first小于second (不用sort result
		Tarjan
		我碰到的是要输出List<PairInt>，这里的PairInt中的first和second有顺序要求，也就是说，输入link是(4, 5)，如果它是一个bridge，那就要输出(4, 5)，(5, 4)是不行的。


Merge two sorted list

Deep copy list


Top N competitors mentioned 换成了Toys


Zombie in Matrix 换成了 servers

Search a 2D matrix II  
	find the location of the target in the matrix






top competitors, 之前经常的面经，topK，先通过给的reveiw list计算competitor的freqMap，再用minHeap算个topK。21个test case有两个过不了，整了半天研究不出来。另外对这题不需要考虑什么特殊字符，直接根据空格split每条review里的单词就好了（测试过，没区别）

 critical routers, 这题我之前一直纠结是否要用torjan，我前几天看了看没看懂，我也不想直接抄答案，最后还解释不清楚，我就还是选择了直接暴力解，每次去掉一个点，再dfs计算遍历到的节点数是否==n - 1来判断是否连通。最后只有一个test case没过，不大清楚是什么case

