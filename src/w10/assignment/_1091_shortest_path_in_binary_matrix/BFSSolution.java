package w10.assignment._1091_shortest_path_in_binary_matrix;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BFSSolution {
    int[][] grid;
    int n;
    // 1-dim sequence to depth
    Map<Integer, Integer> depth;
    int[] dx = new int[]{-1, 1, 0, 0, -1, 1, 1, -1};
    int[] dy = new int[]{0, 0, -1, 1, 1, 1, -1, -1};

    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] == 1) return -1;
        else if (grid.length == 1) return 1;

        this.grid = grid;
        n = grid.length;
        depth = new HashMap<>();

        depth.put(0, 1);

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});

        while (!q.isEmpty()) {
            int[] cur = q.remove();
            // System.out.println(Arrays.toString(cur));
            int curdepth = depth.get(num(cur[0], cur[1]));

            for (int i = 0; i < 8; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                int seq = num(nx, ny);
                if (!depth.containsKey(seq) && isValid(nx, ny)) {
                    if (nx == n - 1 && ny == n - 1) return curdepth + 1;
                    depth.put(seq, curdepth + 1);
                    q.add(new int[]{nx, ny});
                }
            }
        }

        return -1;
    }

    int num(int i, int j) {
        return i * n + j;
    }

    boolean isValid(int i, int j) {
        return i >= 0 && i < n && j >= 0 && j < n && grid[i][j] == 0;
    }
}
