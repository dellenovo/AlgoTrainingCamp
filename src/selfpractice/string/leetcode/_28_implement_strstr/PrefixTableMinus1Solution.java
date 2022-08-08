package selfpractice.string.leetcode._28_implement_strstr;

public class PrefixTableMinus1Solution {
    public int strStr(String haystack, String needle) {
        //build next array whose element is the longest equivalent prefix - 1
        char[] hs = haystack.toCharArray(), nd = needle.toCharArray();

        int[] next = buildNext(nd);

        int j = -1;

        for (int i = 0; i < hs.length; i++) {
            while (j >= 0 && hs[i] != nd[j + 1]) {
                j = next[j];
            }

            if (hs[i] == nd[j + 1]) {
                j++;
            }

            if (j == nd.length - 1) {
                return i - nd.length + 1;
            }
        }

        return -1;
    }

    private int[] buildNext(char[] nd) {
        int[] next = new int[nd.length];
        //问：为什么next中记录的是最长相等前缀数-1，而不是直接记最长相等前缀数呢？
        //答：本质原因还是数组下标从0开始，导致从1开始的第N个数，实际上是从0开始的第N-1个数。
        //另外注意，当j初始化为-1时，和后缀开始处比较时，是把j往前看一位的元素比较的（即j + 1）。
        // j == -1表示，此时只有一个字符，比较前后缀没有意义。
        int j = -1;
        next[0] = j;

        for (int i = 1; i < next.length; i++) {
            while (j >= 0 && nd[i] != nd[j + 1]) {
                j = next[j];
            }

            if (nd[i] == nd[j + 1]) {
                j++;
            }

            next[i] = j;
        }
        return new int[0];
    }

    public static void main(String[] args) {
        PrefixTableMinus1Solution pts = new PrefixTableMinus1Solution();
        String haystack = "hello";
        String needle = "ll";

        System.out.println(pts.strStr(haystack, needle));
    }
}
