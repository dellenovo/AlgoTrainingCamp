package w9.assignment._58_length_of_last_word;

public class Solution {
    public int lengthOfLastWord(String s) {
        int ans = 0;
        boolean start = false;
        char[] chars = s.toCharArray();

        for (int i = chars.length - 1; i >= 0; i--) {
            if (!start && chars[i] != ' ') {
                ans++;
                start = true;
                continue;
            }

            if (start && chars[i] != ' ') {
                ans++;
                continue;
            }

            if (start && chars[i] == ' ') break;
        }

        return ans;
    }
}
