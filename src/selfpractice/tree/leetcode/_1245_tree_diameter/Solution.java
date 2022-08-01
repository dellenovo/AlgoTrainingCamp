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

        //2. convert tree to an out array
        List[] out = new List[n];
        for (int i = 0; i < n; i++) {
            out[i] = new LinkedList<>();
        }

        for (int i = 0; i < tree.length; i++) {
            int x = tree[i][0];
            int y = tree[i][1];
            out[x].add(y);
            out[y].add(x);
        }

//        System.out.println("out:\n" + Arrays.toString(out));

        //3. BFS from the first node to find the furthest node P
        // DFS
        NodeInfo pNodeInfo = dfs(out, 0);

        //4. BFS from the node P to find the furthest node
        // DFS
        return bfs(out, pNodeInfo.seq).depth;
    }

    private static NodeInfo dfs(List<Integer>[] out, int seq) {
        int[] depths = new int[out.length];
        Arrays.fill(depths, -1);
        depths[seq] = 0;
        dfs(out, depths, seq, 0);

        //compare and try to replace the max depth
        int ans = 0;
        for (int i = 1; i < depths.length; i++) {
            if (depths[ans] < depths[i]) ans = i;
        }

        return new NodeInfo(ans, depths[ans]);
    }

    private static void dfs(List<Integer>[] out, int[] depths, int seq, int curdepth) {
//        if (out[seq].size() == 0 || allVisited(out, depths, seq)) return;
        // dfs the tree from node seq
        depths[seq] = curdepth;

        //increase and record depth until the end
        for (int i: out[seq]) {
            if (depths[i] != -1) continue;
            dfs(out, depths, i, curdepth + 1);
        }

    }

//    private static boolean allVisited(List<Integer>[] out, int[] depth, int seq) {
//        for (int child: out[seq]) {
//            if (depth[child] == -1) return false;
//        }
//        return true;
//    }

    private static NodeInfo bfs(List<Integer>[] out, int seq) {
//        System.out.println("start with " + seq);
        int n = out.length;
        int[] depths = new int[n];
        for (int i = 0; i < n; i++) {
            depths[i] = -1;
        }

        Queue<Integer> q = new LinkedList<>();
        depths[seq] = 0;
        q.add(seq);

        while (!q.isEmpty()) {
            int node = q.remove();
//            System.out.println("visit node " + node);

            for (Integer y: out[node]) {
                if (depths[y] != -1) continue;
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
