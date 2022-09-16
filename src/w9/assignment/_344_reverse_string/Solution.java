package w9.assignment._344_reverse_string;

public class Solution {
    public void reverseString(char[] s) {
        int l = 0, r = s.length - 1;

        while (l < r) {
            char t = s[l];
            s[l++] = s[r];
            s[r--] = t;
        }
    }
}