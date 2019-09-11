106. Construct BT from Inorder and Postorder Traversal

基本同 105 就是 从 postorder[postHigh] 取root


inorder = [9,3,15,20,7]
postorder = [9,15,7,20,3]


    3
   / \
  9  20
    /  \
   15   7


class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (postorder.length != inorder.length) return null;
        return build(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] postorder, int postLow, int postHigh, int[] inorder, int inLow, int inHigh) {
    	if (postLow > postHigh || inLow > inHigh) return null;
    	TreeNode root = new TreeNode(postorder[postHigh]);

    	int inorderRoot = inLow;	
    	for (int i = 0; i < inorder.length; i++) {
    		if (inorder[i] == postorder[postHigh]) {
    			inorderRoot = i;
    			break;
    		}
    	}

    	int rightTreeLen = inHigh - inorderRoot;
    	root.left = build(postorder, postLow, postHigh - rightTreeLen - 1, inorder, inLow, inorderRoot - 1);
    	root.right = build(postorder, postHigh - rightTreeLen, postHigh - 1, inorder, inorderRoot + 1, inHigh);
    	return root;
    }
}
