把大问题 => 相同的问题 仅仅是规模变小
	recursive 

	e.g. : 1. mergesort : To sort a big list of elements, you can sort two smaller lists.
			2. selection : To select from a big list, you can partition and select from a smaller list.

	1. Start at the large problem instance.
	2. Work your way back to smaller instances.
	3. ... eventually to base cases that are trivial.

DP 反向相反

Ingredients : 
1. overlapping subproblems
	 一些 子问题 出现多于一次

2. small space of subproblems
	处理这些子问题不会花太长时间
	通常是多项式规模的	polynomial int the problem size

