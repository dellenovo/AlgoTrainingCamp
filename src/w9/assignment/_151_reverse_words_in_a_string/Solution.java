package w9.assignment._151_reverse_words_in_a_string;

public class Solution {
    public String reverseWords(String s) {
        char[] cs = s.toCharArray();

        // remove extra spaces
        int start = 0;

        while (cs[start] == ' ') start++;

        int slow = start;
        int fast = slow;

        int end = cs.length - 1;

        while (cs[end] == ' ') end--;

        while (fast <= end) {
            while (fast > start && fast <= end && cs[fast - 1] == ' ' && cs[fast] == ' ') fast++;
            if (fast <= end) {
                cs[slow++] = cs[fast++];
            }
        }

        end = slow - 1;
        // reverse the whole string
        reverse(cs, start, end);

        // reverse each word
        int l = start, r = start;

        // System.out.println(String.format("start reverse each word!start: %d, end: %d", start, end));

        while (r < end) {
            // System.out.println("reverse each word!" + r);
            while (r < end && cs[r + 1] != ' ') r++;
            reverse(cs, l, r);
            l = r = r + 2;
        }

        char[] ans = new char[end - start + 1];
        System.arraycopy(cs, start, ans, 0, end - start + 1);
        return new String(ans);
    }

    void reverse(char[] cs, int start, int end) {
        int l = start, r = end;

        while (l < r) {
            char t = cs[l];
            cs[l++] = cs[r];
            cs[r--] = t;
        }
    }
}
