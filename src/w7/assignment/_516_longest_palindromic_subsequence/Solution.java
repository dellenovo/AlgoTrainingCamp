package w7.assignment._516_longest_palindromic_subsequence;

public class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] p = new int[n][n];

        for (int i = 0; i < n; i++) p[i][i] = 1;

        for (int len = 1; len <= n; len++)
            for (int l = 0; l <= n -len; l++) {
                int r = l + len - 1;
                if (r == l) continue;
                if (s.charAt(l) == s.charAt(r)) {
                    if (l + 1 == r) p[l][r] =  2;
                    else p[l][r] = p[l + 1][r - 1] + 2;
                } else {
                    p[l][r] = Math.max(p[l + 1][r], p[l][r - 1]);
                }
            }

        return p[0][n - 1];
    }
}
