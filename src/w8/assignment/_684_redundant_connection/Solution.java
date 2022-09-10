package w8.assignment._684_redundant_connection;

public class Solution {
    int[] fa;

    public int[] findRedundantConnection(int[][] edges) {
        int n = 0;

        for (int[] e : edges) n = Math.max(n, Math.max(e[0], e[1]));

        fa = new int[n + 1];

        for (int i = 0; i <= n; i++) fa[i] = i;

        for (int i = 0; i < edges.length; i++) {
            int[] e = edges[i];
            int x = e[0], y = e[1];
            x = find(x);
            y = find(y);

            if (x == y) return e;
            fa[x] = y;
        }

        return null;
    }

    int find(int x) {
        if (x == fa[x]) return x;
        return fa[x] = find(fa[x]);
    }
}
