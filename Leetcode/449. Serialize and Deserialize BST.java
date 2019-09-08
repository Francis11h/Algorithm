449. Serialize and Deserialize BST


297. Serialize and Deserialize Binary Tree 的方法可以用

就是不考虑 BST的结构 直接当一个 binary tree用

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serial(root, sb);
        return sb.toString();
    }
    
    private void serial(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#,");
        } else {
            sb.append(root.val + ",");
            serial(root.left, sb);
            serial(root.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] str = data.split(",");
        List<String> list = new LinkedList<>(Arrays.asList(str));
        return deserial(list);
    }
    
    private TreeNode deserial(List<String> list) {
        if (list.get(0).equals("#")) {
            list.remove(0);
            return null;
        } 
        
        TreeNode root = new TreeNode(Integer.parseInt(list.get(0)));
        list.remove(0);
        root.left = deserial(list);
        root.right = deserial(list);
        return root;
    }
}


既然给了 BST 就要用它的性质

There is no need to use "#" or "null" in BST,which makes it more compact
The reason is that we can reconstruct BST by only using preorder(/postorder/levelorder) traversal.

deserialize 的时候
use Upper and Lower boundaries to check whether we should add null





public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serial(root, sb);
        return sb.toString();
    }
    
    private void serial(TreeNode root, StringBuilder sb) {
        if (root == null) return;
        sb.append(root.val + ",");
        serial(root.left, sb);
        serial(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 0) return null;			//这句要加上 因为 之前普通BT 为null的话 str是 “#,” BST的serial的话 str是 null 会有NPE 
        String[] str = data.split(",");
        List<String> list = new LinkedList<>(Arrays.asList(str));
        return deserial(list, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    private TreeNode deserial(List<String> list, int lower, int upper) {
        if (list.size() == 0) return null;				//这句也要加上 因为 如果length 是0的话 取不到第一个元素
        int val = Integer.parseInt(list.get(0));
        if (val < lower || val > upper) return null;
        TreeNode root = new TreeNode(val);
        list.remove(0);
        root.left = deserial(list, lower, val);
        root.right = deserial(list, val, upper);
        return root;
    }
}





