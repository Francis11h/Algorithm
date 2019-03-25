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

2. small (polynomial-sized) space of subproblems
	处理这些子问题不会花太长时间
	通常是多项式规模的	polynomial int the problem size

3. optimal substructure	最优子结构
	一个问题的最优解 包含其子问题的最优解
	you can compute an optimal solution to a larger problem instance by 
	composing optimal solutions to one or more smaller instances.

4. subproblem independence	子问题独立 (不可互相干扰)
	using an optimal solution to one should not prevent using optimal solutions to other subproblems.












dp 四步走

第一步 确定状态: 即确定dp数组 是几维, 每个参数(i, j, dp[i][j]的cell里存的值)的意义
				原问题，子问题
第二步 转移方程: e.g. LCS 的 :f[i][j] = max{f[i-1][j], f[i][j-1], 
										  f[i-1][j-1]+1 when (A[i-1]=B[j-1])}

第三步 初始条件 (base case) 和 边界问题(最后结果是哪个cell)
		e.g. LCS:	f[0][j] = 0, j=0..n 			空串 和任何串的最长公共子序列长度是0
					f[i][0] = 0, i=0..m

第四步 计算顺序

		• f[0][0], f[0][1], ..., f[0][n]
		• f[1][0], f[1][1], ..., f[1][n]
		•...
		•...
		• f[m][0], f[m][1], ..., f[m][n]
		答案是f[m][n]
		• 此种时间复杂度(计算步数)O(MN)，空间复杂度(数组大小)O(MN)
