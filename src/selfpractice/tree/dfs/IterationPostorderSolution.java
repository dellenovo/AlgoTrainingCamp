package selfpractice.tree.dfs;

import domain.TreeNode;
import util.TreeBuilder;

import java.util.*;

public class IterationPostorderSolution {
    /**
     * mark node
     * @param node
     * @return
     * @param <T>
     */
    public static <T> List<T> dfsPostorder(TreeNode<T> node) {
        List<T> ans = new LinkedList<>();

        if (node == null) return ans;

        TreeNode NULL_NODE = new TreeNode(null);

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            TreeNode<T> cur = stack.pop();

            if (cur == NULL_NODE) {
                cur = stack.pop();
                ans.add(cur.val);
            } else {
                stack.push(cur);
                stack.push(NULL_NODE);
                if (cur.right != null) stack.push(cur.right);
                if (cur.left != null) stack.push(cur.left);
            }
        }

        return ans;
    }

    /**
     * visited set
     * @param root
     * @return
     * @param <T>
     */
    public static <T> List<T> dfsPostOrder2(TreeNode<T> root) {
        List<T> ans = new ArrayList<>();
        if (root == null) return ans;

        Set<TreeNode> visited = new HashSet<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode<T> node = stack.pop();

            if (visited.contains(node)) {
                ans.add(node.val);
            } else {
                visited.add(node);
                stack.push(node);
                if (node.right != null) stack.push(node.right);
                if (node.left != null) stack.push(node.left);
            }
        }

        return ans;
    }

    /**
     * reverse a modified preorder traversal result
     * @param root
     * @return
     * @param <T>
     */
    public static <T> List<T> dfsPostOrder3(TreeNode<T> root) {
        List<T> ans = new LinkedList<>();

        if (root == null) return ans;

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode<T> cur = stack.pop();

            ans.add(cur.val);
            //Pay attention: push & add. If the deque is a stack, remember only to use push & pop.
            if (cur.left != null) stack.push(cur.left);
            if (cur.right != null) stack.push(cur.right);
        }

        for(T i : ans) {
            System.out.print(i + " ");
        }

        System.out.println("\nAfter reverse");

        Collections.reverse(ans);
        return ans;
    }

    public static void main(String[] args) {
        TreeNode<Integer> root = TreeBuilder.buildFromBFS(new Integer[]{1, 3, 2, null, null, null, 4});
        List<Integer> ans = dfsPostOrder2(root);

        for(Integer i : ans) {
            System.out.print(i + " ");
        }
    }
}
