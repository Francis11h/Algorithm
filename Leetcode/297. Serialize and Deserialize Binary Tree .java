297. Serialize and Deserialize Binary Tree


设计一个算法，并编写代码来序列化和反序列化二叉树。

将树写入一个文件被称为“序列化”，读取文件后重建同样的二叉树被称为“反序列化”。

如何反序列化或序列化二叉树是没有限制的，你只需要确保可以将二叉树序列化为一个字符串，并且可以将字符串反序列化为原来的树结构


You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"


Note: Do not use class member/global/static variables to store states.
            不要使用 类的 成员 / 全局 / 静态变量 来存储状态 
Your serialize and deserialize algorithms should be stateless（无状态）.
    总之 就是 不要用 类变量 




不能用类变量 的话 就是 要有一个 容器 先把tree遍历一遍存起来 然后 再拿住来组成字符串, 
因为 无法递归传值

所以 既然还要 层序遍历 自然想到 bfs 用 queue 存节点, 将当前节点的左右儿子依次存入 queue,
每次 poll 出来 节点后 判断是不是 null 是的话 把 "#" 加入 ans


deserialize()首先切割字符串，然后用isLeftChild标记是当前是左右儿子，
数字转化为字符串，存为队列首节点的左右儿子。


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "[]";
        //List 来模仿 queue
        ArrayList<TreeNode> queue = new ArrayList<>();
        queue.add(root);
        //这里 queue.size() 会不断变
        for (int i = 0; i < queue.size(); i++) {
            TreeNode node = queue.get(i);
            if (node == null) {
                continue;
            }
            queue.add(node.left);
            queue.add(node.right);
        }

        while (queue.get(queue.size() - 1) == null) {
            queue.remove(queue.size() - 1);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(queue.get(0).val);
        for (int i = 1; i < queue.size(); i++) {
            if (queue.get(i) == null) {
                sb.append(",#");
            } else {
                sb.append(",");
                sb.append(queue.get(i).val);
            }
        }
        sb.append("]");

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("[]")) return null;

        String[] nodeList = data.substring(1, data.length() - 1).split(",");
        ArrayList<TreeNode> queue = new ArrayList<>();              //也需要数组！！！

        TreeNode root = new TreeNode(Integer.parseInt(nodeList[0]));
        queue.add(root);

        int index = 0;
        boolean isLeftChild = true;

        for (int i = 1; i < nodeList.length; i++) {                 //1 开始
            if (!nodeList[i].equals("#")) {     //比较用 .equals
                TreeNode node = new TreeNode(Integer.parseInt(nodeList[i]));
                if (isLeftChild) {
                    queue.get(index).left = node;           //index 不是 i, index指的是父亲节点
                } else {
                    queue.get(index).right = node;
                }
                queue.add(node);              //这个得加！！！别忘了 ！！！！！
            }

            if (!isLeftChild) {                 //不能一开始就加 
                index++;
            }
            isLeftChild = !isLeftChild;
        }
        return root;
    }
}



















