package w9.assignment._14_longest_common_prefix;

public class Solution {
    public String longestCommonPrefix(String[] strs) {
        int i = 0;

        OUT: while (true) {
            for (int j = 0; j < strs.length; j++)
                if (i > strs[j].length() - 1) break OUT;
                else if ( j > 0 && strs[j - 1].charAt(i) != strs[j].charAt(i)) break OUT;
            i++;
        }

        return strs[0].substring(0, i);
    }
}
