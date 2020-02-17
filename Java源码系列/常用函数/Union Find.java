逻辑上是树结构，实际还是数组实现 即可

初始化 father数组
private int[] father = null;

find 是一种两趟方法，当它递归时， 第一趟 沿着 查找路径向上直到找到根
第二趟 沿着搜索树向下更新每个节点，使其直接指向根。

private int find(int x) {
    if (father[x] == x) {
        return x;
    }
    return father[x] = find(father[x]);
}
/* 方法二
private int find(int x) {
    if (father[x] != x) {
        father[x] = find(father[x]);
    }
    return father[x];
}*/

union 合并

private void union(int a, int b) {
    int rootA = find(a), rootB = find(b);
    if (rootA != rootB) {
        father[rootA] = rootB;
        //这里会有计数加减操作
    }
}