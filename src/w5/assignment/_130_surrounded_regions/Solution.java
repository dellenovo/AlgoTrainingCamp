package w5.assignment._130_surrounded_regions;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    char[][] board;
    int m, n;
    boolean[] visited;
    Deque<int[]> q;
    int[] dx = new int[]{-1, 1, 0, 0};
    int[] dy = new int[]{0, 0, -1, 1};

    public void solve(char[][] board) {
        this.board = board;
        m = board.length;
        n = board[0].length;
        visited = new boolean[m * n];

        q = new LinkedList<>();

        // find O on the boarder
        for(int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                enque(i, 0);
            }

            if (board[i][n - 1] == 'O') {
                enque(i, n - 1);
            }
        }

        for(int j = 1; j < n - 1; j++) {
            if (board[0][j] == 'O') {
                enque(0, j);
            }

            if (board[m - 1][j] == 'O') {
                enque(m - 1, j);
            }
        }

        bfs();

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (board[i][j] == 'O' && !visited[num(i, j)])
                    board[i][j] = 'X';
    }

    void bfs() {
        while(!q.isEmpty()) {
            int[] cell = q.remove();

            for (int i = 0; i < 4; i++) {
                int nx = cell[0] + dx[i];
                int ny = cell[1] + dy[i];
                int no = num(nx, ny);
                if (valid(nx, ny) && board[nx][ny] == 'O' && !visited[no]) {
                    visited[no] = true;
                    q.add(new int[]{nx, ny});
                }
            }
        }
    }

    boolean valid(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    void enque(int x, int y) {
        int no = num(x, y);
        visited[no] = true;
        q.add(new int[]{x, y});
    }

    int num(int x, int y) {
        return x * n + y;
    }
}
