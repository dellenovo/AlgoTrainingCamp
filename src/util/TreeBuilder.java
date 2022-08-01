package util;

import domain.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class TreeBuilder {
    public static <T extends Comparable<T>> TreeNode<T> buildFromBFS(T[] nodes) {
        if (nodes == null || nodes.length == 0 || nodes[0] == null) return null;
        
        TreeNode<T> root = new TreeNode<>(nodes[0]);
        
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);
        
        int pos = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                TreeNode<T> parent = queue.remove();
                if ( pos < nodes.length) {
                    if (nodes[pos] != null) {
                        parent.left = new TreeNode<>(nodes[pos]);
                        queue.add(parent.left);
                    }
                    pos++;
                }
                if (pos < nodes.length) {
                    if (nodes[pos] != null){
                        parent.right = new TreeNode<>(nodes[pos]);
                        queue.add(parent.right);
                    }
                    pos++;
                }
            }
        }

        return root;
    }

    public static void main(String[] args) {
        Integer[][] trees = new Integer[][]{
                {1, 2, 3, 4, null, null, 5, null, null},
                {null},
                {},
                {9, 8, 7, 6, 5, 4, 3}
        };

        for (Integer[] tree: trees) {
            TreeNode<Integer> root = buildFromBFS(tree);
            System.out.println(root);
        }
    }
}
