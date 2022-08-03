package selfpractice.tree.dfs;

import domain.GenericTreeNode;
import util.TreeBuilder;

import java.util.LinkedList;
import java.util.List;

public class RecursiveInorderSolution {
    public static <T> void dfsInorder(GenericTreeNode<T> root, List<T> ans) {
        if (root == null) return;

        dfsInorder(root.left, ans);
        ans.add(root.val);
        dfsInorder(root.right, ans);
    }

    public static void main(String[] args) {
        GenericTreeNode<Integer> root = TreeBuilder.buildFromBFS(new Integer[]{1, 3, 2, null, null, null, 4});
        List<Integer> ans = new LinkedList<>();
        dfsInorder(root, ans);

        for(Integer i : ans) {
            System.out.print(i + " ");
        }
    }
}
