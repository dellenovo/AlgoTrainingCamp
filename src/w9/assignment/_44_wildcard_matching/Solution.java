package w9.assignment._44_wildcard_matching;

public class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        String sn = " " + s;
        String pn = " " + p;

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;

        for (int j = 1; j <= n; j++)
            if (pn.charAt(j) == '*')
                for (int i = 0; i <= m; i++)
                    f[i][j] = true;
            else break;

        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++)
                if (pn.charAt(j) >= 'a' && pn.charAt(j) <= 'z')
                    f[i][j] = sn.charAt(i) == pn.charAt(j) && f[i - 1][j - 1];
                else if (pn.charAt(j) == '?')
                    f[i][j] = f[i - 1][j - 1];
                else if (pn.charAt(j) == '*') {
                    f[i][j] = f[i][j - 1];
                    f[i][j] = f[i][j] || f[i - 1][j];
                }

        return f[m][n];
    }
}
