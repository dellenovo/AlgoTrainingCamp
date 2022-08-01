package selfpractice.tree.dfs;

import domain.TreeNode;
import util.TreeBuilder;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class IterationPreorderSolution {
    public static <T> List<T> dfsPreorder(TreeNode<T> root) {
        List<T> ans = new LinkedList<>();

        if (root == null) return ans;

        Deque<TreeNode<T>> stack = new ArrayDeque<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode<T> cur = stack.pop();
            ans.add(cur.val);
            if (cur.right != null) stack.push(cur.right);
            if (cur.left != null) stack.push(cur.left);
        }

        return ans;
    }

    public static void main(String[] args) {
        TreeNode<Integer> root = TreeBuilder.buildFromBFS(new Integer[]{1, 3, 2, null, null, null, 4});
        List<Integer> ans = dfsPreorder(root);

        for(Integer i : ans) {
            System.out.print(i + " ");
        }
    }
}
