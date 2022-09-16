package w9.assignment._686_repeated_string_match;

public class Solution {
    public int repeatedStringMatch(String a, String b) {
        int an = a.length(), bn = b.length();

        int index = strStr(a, b);

        if (index == -1) return -1;
        if (index <= an - bn) return 1;
        return (bn + index - an) % an == 0 ? (bn + index - an)/an + 1 : (bn + index - an)/an + 2;
    }

    int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();

        int[] next = new int[m];
        next[0] = -1;

        for (int i = 1, j = -1; i < m; i++) {
            while (j >= 0 && needle.charAt(i) != needle.charAt(j + 1)) j = next[j];
            if (needle.charAt(i) == needle.charAt(j + 1)) j++;
            next[i] = j;
        }

        for (int i = 0, j = -1; i - j <= n; i++) {
            while (j >= 0 && haystack.charAt(i % n) != needle.charAt(j + 1)) j = next[j];

            if (haystack.charAt(i % n) == needle.charAt(j + 1)) j++;

            if (j == m - 1) return i - m + 1;
        }

        return -1;
    }
}
