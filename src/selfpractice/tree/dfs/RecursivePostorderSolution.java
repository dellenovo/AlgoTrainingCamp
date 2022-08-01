package selfpractice.tree.dfs;

import domain.TreeNode;
import util.TreeBuilder;

import java.util.LinkedList;
import java.util.List;

public class RecursivePostorderSolution {

    public static <T> void dfsPostorder(TreeNode<T> node, List<T> ans) {
        if (node == null) return;
        dfsPostorder(node.left, ans);
        dfsPostorder(node.right, ans);
        ans.add(node.val);
    }

    public static void main(String[] args) {
        TreeNode<Integer> root = TreeBuilder.buildFromBFS(new Integer[]{1, 3, 2, null, null, null, 4});
        List<Integer> ans = new LinkedList<>();
        dfsPostorder(root, ans);

        for(Integer i : ans) {
            System.out.print(i + " ");
        }
    }
}
