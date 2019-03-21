DFS
适用场景: 寻找所有方案。 注意 是 所有 
找 所有方案 的题，基本可以确定是DFS

除了二叉树以外的 90% DFS的题，要么是  排列premutation ,要么是 组合combination

vs BFS :  BFS适合 寻找最小的状态转换次数(先搜索到的结果一定是最优的)

DFS 通常采用，搜索 + 回溯 来实现    search + backtracking


• 先确定DFS的对象是什么(格子，课程，数字...)
• 需要开数组存储哪些位置/元素已经被访问
• 递归中用循环选择下一个符合条件的  位置/元素
• 循环内:
    – 标记访问
    – 递归
    – 标记未访问 (回溯!!!)

算法导论书中定义 

生成的是 DFS树的森林，不能保证每个点都走到，所以啊，外层有个循环
//outer loop
DFS()
    set all Vertices to white, with timestamps and predecessor undefined
    time = 0;
    for each Vertex u
        if u is white
            recursiveDFS(u);

//真正的递归搜索部分 inner loop
recursiveDFS(u) 
    u.discovery = (time += 1)
    color u gray        //染上灰色，表示已经到达了，不再是白色，不会再进loop 保证每个点只走一次

    for all edges u -> j
        if j is white
            set u as j‘s predecessors;
            recursiveDFS(j)
    color u black       //表示对u的操作处理完成(u的临接表中每条边都走过了)
    u.finish = (time += 1)

T : O(V + E)    每个点每条边都只走一次       //O(V^2) 稠密图的话





