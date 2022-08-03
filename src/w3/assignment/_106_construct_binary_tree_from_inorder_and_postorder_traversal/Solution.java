package w3.assignment._106_construct_binary_tree_from_inorder_and_postorder_traversal;

import domain.TreeNode;

public class Solution {
    int[] inorder;
    int[] postorder;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.inorder = inorder;
        this.postorder = postorder;

        return buildTree(0, inorder.length, 0, postorder.length);
    }

    TreeNode buildTree(int inStart, int inEnd, int postStart, int postEnd) {
        if (inStart >= inEnd) return null;

        TreeNode root = new TreeNode(postorder[postEnd - 1]);

        int rootPos = inStart;

        while(rootPos < inEnd) {
            if (inorder[rootPos] == root.val) break;
            rootPos++;
        }

        if (rootPos == inEnd) throw new RuntimeException("Cannot find root in inorder sequence.");

        root.left = buildTree(
                inStart, rootPos, postStart, postStart + rootPos - inStart
        );
        root.right = buildTree(
                rootPos + 1, inEnd, postStart + rootPos - inStart, postEnd - 1
        );

        return root;
    }
}
