package w9.assignment._917_reverse_only_letters;

public class Solution {
    public String reverseOnlyLetters(String s) {
        char[] cs = s.toCharArray();

        int l = 0, r = cs.length - 1;

        while (l < r) {
            while (l < r && !isLetter(cs[l])) l++;
            while (l < r && !isLetter(cs[r])) r--;

            if (l < r) {
                char t = cs[l];
                cs[l++] = cs[r];
                cs[r--] = t;
            }
        }

        return new String(cs);
    }

    boolean isLetter(char c) {
        return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
    }
}
