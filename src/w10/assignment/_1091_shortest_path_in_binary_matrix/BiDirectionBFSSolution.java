package w10.assignment._1091_shortest_path_in_binary_matrix;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BiDirectionBFSSolution {
    int[][] grid;
    int n;
    // 1-dim sequence to depth
    Map<Integer, Integer> startdepth;
    Map<Integer, Integer> enddepth;
    int[] dx = new int[]{-1, 1, 0, 0, -1, 1, 1, -1};
    int[] dy = new int[]{0, 0, -1, 1, 1, 1, -1, -1};

    public int shortestPathBinaryMatrix(int[][] grid) {
        this.grid = grid;
        n = grid.length;

        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) return -1;
        else if (grid.length == 1) return 1;

        startdepth = new HashMap<>();

        startdepth.put(0, 1);

        enddepth = new HashMap<>();
        enddepth.put(num(n - 1, n - 1), 1);

        Queue<int[]> startq = new LinkedList<>();
        startq.add(new int[]{0, 0});

        Queue<int[]> endq = new LinkedList<>();
        endq.add(new int[]{n - 1, n - 1});

        int ans;
        while (!startq.isEmpty() && !endq.isEmpty()) {
            ans = bfs(startq, startdepth, enddepth);
            if (ans != -1) return ans;
            ans = bfs(endq, enddepth, startdepth);
            if (ans != -1) return ans;
        }

        return -1;
    }

    int bfs(Queue<int[]> thisq, Map<Integer, Integer> thisdepth, Map<Integer, Integer> otherdepth) {
        int sz = thisq.size();

        for (int c = 0; c < sz; c++) {
            int[] cur = thisq.remove();
            int curdepth = thisdepth.get(num(cur[0], cur[1]));
            // System.out.println(String.format("visit (%d, %d) %d", cur[0], cur[1], curdepth));

            for (int i = 0; i < 8; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                int seq = num(nx, ny);
                if (!thisdepth.containsKey(seq) && isValid(nx, ny)) {
                    // System.out.println(String.format("next (%d, %d)", nx, ny));
                    if (otherdepth.containsKey(seq)) return curdepth + otherdepth.get(seq);
                    thisdepth.put(seq, curdepth + 1);
                    thisq.add(new int[]{nx, ny});
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
