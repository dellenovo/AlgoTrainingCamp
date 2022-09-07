package w7.assignment._279_perfect_squares;

import java.util.Arrays;

public class Solution {
    public int numSquares(int n) {
        int sqrt = (int)Math.sqrt(n);

        // 二维动规，f[i][j]表示前i个完全平方数凑成和为j的最少完全平方数的个数
        int[][] f = new int[sqrt + 1][n + 1];

        for (int i = 0; i <= sqrt; i++) Arrays.fill(f[i], Integer.MAX_VALUE);
        f[0][0] = 0;

        for (int i = 1; i <= sqrt; i++){
            for (int j = 0; j <= n; j++) f[i][j] = f[i - 1][j];
            for (int j = i * i; j <= n; j++) {
                f[i][j] = Math.min(f[i][j], f[i][j - i * i] + 1);
            }
        }

        return f[sqrt][n];
    }
}
