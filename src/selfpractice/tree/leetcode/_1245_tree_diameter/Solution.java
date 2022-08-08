package selfpractice.tree.leetcode._1245_tree_diameter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    /**
     *
     * @param tree the tree array saves all edges in the no-root tree, e.g. {{0, 1}, {0, 2}}
     * @return
     */
    public static int treeDiameter(int[][] tree) {
        //1. get node number
        int n = 0;
        for (int[] ints : tree) {
            int x = ints[0];
            int y = ints[1];
            n = Math.max(n, Math.max(x, y));
        }
        n++;
//        System.out.println("n:" + n);

        //2. convert tree to an out-edge array 出边数组
        List[] out = new List[n];
        for (int i = 0; i < n; i++) {
            out[i] = new LinkedList<>();
        }

        for (int i = 0; i < tree.length; i++) {
            int x = tree[i][0];
            int y = tree[i][1];
            // remember to add edges for both direction
            out[x].add(y);
            out[y].add(x);
        }

        //问：这里为什么不需要找根，然后从根开始找最深节点呢？
        //答：因为没有必要。按照证明，从任意节点出发的最长路径的端点，必然是整个树最长路径的一头。所以不需要从根出发。
        //问：是否一定要用depths数组记录每个节点的深度？
        //答：因为我们是用图的数据结构来表示这棵树，所以没有父子关系的体现。为了避免重复访问节点，每个节点都需要一个标记。那么正好可以用这个节点
        //   来记录每个节点的深度。初始值为-1，表示没有被访问过。

//        System.out.println("out:\n" + Arrays.toString(out));

        //3. BFS from the first node to find the furthest node P
        // DFS
        NodeInfo pNodeInfo = bfs(out, 0);

        //4. BFS from the node P to find the furthest node
        // DFS
        return bfs(out, pNodeInfo.seq).depth;
    }

    private static NodeInfo dfs(List<Integer>[] out, int seq) {
        int[] depths = new int[out.length];
        //a quick way to fill arrays
        //Why must the initail value be -1?
        //Because we use -1 as a mark of not being visited yet.
        Arrays.fill(depths, -1);
        //the depth of the start node is 0
        depths[seq] = 0;
        //start dfs recursion
        dfs(out, depths, seq, 0);

        //compare and try to replace the max depth
        //We only need to save the index of the max value, don't need to save the max value itself.
        int ans = 0;
        for (int i = 1; i < depths.length; i++) {
            if (depths[ans] < depths[i]) ans = i;
        }

        return new NodeInfo(ans, depths[ans]);
    }

    private static void dfs(List<Integer>[] out, int[] depths, int seq, int curdepth) {
        // There seems no obvious termination condition in the recursion. Why?
        // Because finally all nodes will be visited, so the following for-loop will be skipped.

        // dfs the tree from node seq
        // save the node's depth in depths array
        depths[seq] = curdepth;

        //increase and record depth until the end
        for (int i: out[seq]) {
            //If the node is visited, skip it.
            if (depths[i] != -1) continue;
            //As we dive into the next-level node, the depth increases by 1.
            dfs(out, depths, i, curdepth + 1);
        }

    }

    private static NodeInfo bfs(List<Integer>[] out, int seq) {
//        System.out.println("start with " + seq);
        int n = out.length;
        int[] depths = new int[n];
        Arrays.fill(depths, -1);
        Queue<Integer> q = new LinkedList<>();
        depths[seq] = 0;
        q.add(seq);

        while (!q.isEmpty()) {
            int node = q.remove();
//            System.out.println("visit node " + node);

            for (Integer y: out[node]) {
                if (depths[y] != -1) continue;
                // the depth of y is the depth of its parent adding 1
                depths[y] = depths[node] + 1;
                q.add(y);
            }
        }

        int ans = 0;
        for (int i = 1; i < n; i++) {
            if (depths[i] > depths[ans]) {
                ans = i;
            }
        }
        return new NodeInfo(ans, depths[ans]);
    }

    public static void main(String[] args) {
        int[][][] trees = {
                {{0, 1}, {0, 2}},
                {{0, 1}, {1, 2}, {2, 3}, {1, 4}, {4, 5}},
        };
        for (int[][] tree: trees)
            System.out.println(treeDiameter(tree));
    }
}
class NodeInfo {
    int seq;
    int depth;

    public NodeInfo(int seq, int depth) {
        this.seq = seq;
        this.depth = depth;
    }
}
