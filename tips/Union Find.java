Disjoint Set

每个集合都有个大哥
	find(x) : return the representative for the set containing x.

合并 x, y所在的集合
	union(x, y) : merge the sets containing elements x and y


Path Compression 路径压缩算法 : 使查找路径中每个节点直接指向根
 				flattens the structure of the tree by 
 				making every node point to the root whenever Find is used on it

	find(x)
		if x.parent != x
			return x.parent = find(x.parent);
		return x.parent;





Union-Find
逻辑上是树结构，实际还是数组实现 即可

并查集 标准模版 :

1. 初始化 father数组
private int[] father = null;

2. find 是一种两趟方法，当它递归时， 第一趟 沿着 查找路径向上直到找到根
		第二趟 沿着搜索树 向下更新每个节点，使其直接指向根。
private int find(int x) {
	if (father[x] == x) {
		return x;
	}
	return father[x] = find(father[x]);
}

O(n)

3. union 其中包含find

private void union(int a, int b) {
	int rootA = find(a), rootB = find(b);
	if (rootA != rootB) {
		father[rootA] = rootB;
		//会有一些操作 计数 count
	}
}

O(n)
