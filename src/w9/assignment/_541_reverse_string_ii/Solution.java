package w9.assignment._541_reverse_string_ii;

public class Solution {
    public String reverseStr(String s, int k) {
        char[] cs = s.toCharArray();

        for (int i = 0; i < cs.length; i = i + 2 * k)
            if (i + 2 * k <= cs.length || i + k <= cs.length) reverse(cs, i, i + k - 1);
            else reverse(cs, i, cs.length - 1);

        return new String(cs);
    }

    void reverse(char[] cs, int start, int end) {
        while (start < end) {
            char t = cs[start];
            cs[start++] = cs[end];
            cs[end--] = t;
        }
    }
}
