package w3.assignment._538_convert_bst_to_greater_tree;

public class Solution {
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        dfs(root);
        return root;
    }
    void dfs(TreeNode node) {
        if (node == null) return;

        dfs(node.right);
        sum += node.val;
        node.val = sum;
        dfs(node.left);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
