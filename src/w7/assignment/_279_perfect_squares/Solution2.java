package w7.assignment._279_perfect_squares;

import java.util.Arrays;

public class Solution2 {
    public int numSquares(int n) {
        int sqrt = (int)Math.sqrt(n);

        // 一维动规，f[j]表示凑成和为j的最少完全平方数的个数
        int[] f = new int[n + 1];

        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = 0;

        for (int i = 1; i <= sqrt; i++){
            for (int j = i * i; j <= n; j++) {
                f[j] = Math.min(f[j], f[j - i * i] + 1);
            }
        }

        return f[n];
    }
}
