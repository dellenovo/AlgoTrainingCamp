package selfpractice.tree.dfs;

import domain.GenericTreeNode;
import util.TreeBuilder;

import java.util.LinkedList;
import java.util.List;

public class RecursivePreorderSolution {
    public static <T> void dfsPreorder(GenericTreeNode<T> node, List<T> ans) {
        if (node == null) return;
        ans.add(node.val);
        dfsPreorder(node.left, ans);
        dfsPreorder(node.right, ans);
    }

    public static void main(String[] args) {
        GenericTreeNode<Integer> root = TreeBuilder.buildFromBFS(new Integer[]{1, 3, 2, null, null, null, 4});
        List<Integer> ans = new LinkedList<>();
        dfsPreorder(root, ans);

        for(Integer i : ans) {
            System.out.print(i + " ");
        }
    }
}
