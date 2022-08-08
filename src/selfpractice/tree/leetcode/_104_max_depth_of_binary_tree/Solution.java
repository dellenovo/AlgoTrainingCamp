package selfpractice.tree.leetcode._104_max_depth_of_binary_tree;

import domain.TreeNode;
import util.TreeBuilder;

public class Solution {
    int maxDepth = 0;
    public int maxDepth(TreeNode root) {
        maxDepth = 0;
        maxDepth(root, 1);
        return maxDepth;
    }

    private void maxDepth(TreeNode node, int depth) {
        if (node == null) return;
        if (depth > maxDepth) maxDepth = depth;

        maxDepth(node.left, depth + 1);
        maxDepth(node.right, depth + 1);
    }

    public static void main(String[] args) {
        Integer[][] trees = {{3,9,20,null,null,15,7},
                {1,null,2}};
        Solution s = new Solution();

        for (Integer[] tree: trees) {
            TreeNode root = TreeBuilder.buildIntegerTreeFromBFS(tree);
            System.out.println(s.maxDepth(root));
        }
    }
}
