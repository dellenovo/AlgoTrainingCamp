package selfpractice.tree.dfs;

import domain.GenericTreeNode;
import util.TreeBuilder;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class IterationInorderSolution {
    /**
     * pointer & stack
     * stack saves the nodes needing tracing back
     * pointer points to the node that may be pushed to the stack and switch to the right child of a node
     * popped from the stack.
     * @param root
     * @return
     * @param <T>
     */
    public static <T> List<T> dfsInorder(GenericTreeNode<T> root) {
        List<T> ans = new LinkedList<>();

        GenericTreeNode<T> cur = root;
        Deque<GenericTreeNode<T>> stack = new ArrayDeque<>();

        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                ans.add(cur.val);
                cur = cur.right;
            }
        }

        return ans;
    }

    /**
     * mark node
     * @param root
     * @return
     * @param <T>
     */
    public static <T> List<T> dfsInorder2(GenericTreeNode<T> root) {
        List<T> ans = new LinkedList<>();

        if (root == null) return ans;

        GenericTreeNode<T> NULL_NODE = new GenericTreeNode<>(null);
        Deque<GenericTreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            GenericTreeNode<T> cur = stack.pop();

            if (cur == NULL_NODE) {
                cur = stack.pop();
                ans.add(cur.val);
            } else {
                if (cur.right != null) stack.push(cur.right);
                stack.push(cur);
                stack.push(NULL_NODE);
                if (cur.left != null) stack.push(cur.left);
            }
        }

        return ans;
    }

    public static <T> List<T> dfsInorderEx(GenericTreeNode<T> root) {
        List<T> ans = new LinkedList<>();

        GenericTreeNode<T> cur = root;
        Deque<GenericTreeNode<T>> stack = new ArrayDeque<>();

        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                ans.add(cur.val);
                cur = cur.right;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        GenericTreeNode<Integer> root = TreeBuilder.buildFromBFS(new Integer[]{1, 3, 2, null, null, null, 4});
        List<Integer> ans = dfsInorder(root);

        for(Integer i : ans) {
            System.out.print(i + " ");
        }
    }
}
