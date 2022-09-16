package w9.assignment._557_reverse_words_in_a_string_iii;

public class Solution {
    public String reverseWords(String s) {
        char[] cs = s.toCharArray();

        int slow = 0, fast = 0;

        while (fast < cs.length) {
            while (fast + 1 < cs.length && cs[fast + 1] != ' ') fast++;
            reverse(cs, slow, fast);
            slow = fast = fast + 2;
        }

        return new String(cs);
    }

    void reverse(char[] cs, int left, int right) {
        while (left < right) {
            char t = cs[left];
            cs[left++] = cs[right];
            cs[right--] = t;
        }
    }
}
