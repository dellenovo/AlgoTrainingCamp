package util;

import domain.GenericTreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class TreeBuilder {
    public static <T extends Comparable<T>> GenericTreeNode<T> buildFromBFS(T[] nodes) {
        if (nodes == null || nodes.length == 0 || nodes[0] == null) return null;
        
        GenericTreeNode<T> root = new GenericTreeNode<>(nodes[0]);
        
        Queue<GenericTreeNode<T>> queue = new LinkedList<>();
        queue.add(root);
        
        int pos = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                GenericTreeNode<T> parent = queue.remove();
                if ( pos < nodes.length) {
                    if (nodes[pos] != null) {
                        parent.left = new GenericTreeNode<>(nodes[pos]);
                        queue.add(parent.left);
                    }
                    pos++;
                }
                if (pos < nodes.length) {
                    if (nodes[pos] != null){
                        parent.right = new GenericTreeNode<>(nodes[pos]);
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
            GenericTreeNode<Integer> root = buildFromBFS(tree);
            System.out.println(root);
        }
    }
}
