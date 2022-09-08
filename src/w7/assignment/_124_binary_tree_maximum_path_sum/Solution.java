package w7.assignment._124_binary_tree_maximum_path_sum;

import domain.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    Map<TreeNode, int[]> m;
    public int maxPathSum(TreeNode root) {
        m = new HashMap<>();
        m.put(null, new int[]{-2000, -2000});

        dfs(root);

        // for (TreeNode n: m.keySet())
        //     System.out.println(n == null ? null : n.val + Arrays.toString(m.get(n)));

        return Math.max(m.get(root)[0], m.get(root)[1]);
    }

    void dfs(TreeNode node) {
        if (node == null) return;

        // if (node.left == null && node.right == null) {
        //     m.put(node, new int[]{-2000, node.val});
        //     return;
        // }

        dfs(node.left);
        dfs(node.right);

        int[] r = new int[2];
        r[0] = Math.max(Math.max(m.get(node.left)[0], m.get(node.left)[1]),
                Math.max(m.get(node.right)[0], m.get(node.right)[1]));

        r[0] = Math.max(r[0], m.get(node.left)[1] + node.val + m.get(node.right)[1]);

        r[1] = Math.max(Math.max(node.val, m.get(node.left)[1] + node.val),
                m.get(node.right)[1] + node.val);

        m.put(node, r);
    }
}
