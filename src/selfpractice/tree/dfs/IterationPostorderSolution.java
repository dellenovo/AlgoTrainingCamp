package selfpractice.tree.dfs;

import domain.GenericTreeNode;
import util.TreeBuilder;

import java.util.*;

public class IterationPostorderSolution {
    /**
     * mark node
     * @param node
     * @return
     * @param <T>
     */
    public static <T> List<T> dfsPostorder(GenericTreeNode<T> node) {
        List<T> ans = new LinkedList<>();

        if (node == null) return ans;

        GenericTreeNode NULL_NODE = new GenericTreeNode(null);

        Deque<GenericTreeNode> stack = new ArrayDeque<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            GenericTreeNode<T> cur = stack.pop();

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
    public static <T> List<T> dfsPostOrder2(GenericTreeNode<T> root) {
        List<T> ans = new ArrayList<>();
        if (root == null) return ans;

        Set<GenericTreeNode> visited = new HashSet<>();
        Deque<GenericTreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            GenericTreeNode<T> node = stack.pop();

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
    public static <T> List<T> dfsPostOrder3(GenericTreeNode<T> root) {
        List<T> ans = new LinkedList<>();

        if (root == null) return ans;

        Deque<GenericTreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            GenericTreeNode<T> cur = stack.pop();

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
        GenericTreeNode<Integer> root = TreeBuilder.buildFromBFS(new Integer[]{1, 3, 2, null, null, null, 4});
        List<Integer> ans = dfsPostOrder2(root);

        for(Integer i : ans) {
            System.out.print(i + " ");
        }
    }
}
