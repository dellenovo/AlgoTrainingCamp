package w8.assignment._200_number_of_islands;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    int[] fa;
    int m, n;
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    public int numIslands(char[][] grid) {
        m = grid.length;
        n = grid[0].length;

        // System.out.println(String.format("m: %d, n: %d", m, n));

        fa = new int[m * n];

        for (int i = 0; i < m * n; i++) fa[i] = i;

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (grid[i][j] == '1') {
                    // System.out.println(String.format("grid[%d][%d]: %s", i, j, grid[i][j]));
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        // System.out.println(String.format("i:%d, j:%d, nx:%d, ny:%d", i, j, ));
                        if (nx < 0 || nx >= m || ny < 0 || ny >= n || grid[nx][ny] == '0') continue;
                        // System.out.println(String.format("union set %d %d", num(i, j), num(nx, ny)));
                        unionSet(num(i, j), num(nx, ny));
                    }
                }

        Set<Integer> ansset = new HashSet<>();

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (grid[i][j] == '1') {
                    ansset.add(find(num(i, j)));
                }

        // System.out.println(Arrays.toString(fa));

        return ansset.size();
    }

    int num(int i, int j) {
        return i * n + j;
    }

    void unionSet(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) fa[x] = y;
    }

    int find(int x) {
        if (x == fa[x]) return x;
        return fa[x] = find(fa[x]);
    }
}
