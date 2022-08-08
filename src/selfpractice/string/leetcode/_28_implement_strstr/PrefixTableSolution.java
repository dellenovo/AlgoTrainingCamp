package selfpractice.string.leetcode._28_implement_strstr;

public class PrefixTableSolution {
    public int strStr(String haystack, String needle) {
        // build next array
        char[] hs = haystack.toCharArray(), nd = needle.toCharArray();
        int[] next = buildNext(nd);

        // use next array to match
        int j = 0;

        for (int i = 0; i < hs.length; i++) {
            while (j > 0 && hs[i] != nd[j]) {
                j = next[j - 1];
            }

            if (hs[i] == nd[j]) {
                j++;
            }

            if (j == nd.length) {
                return i - nd.length + 1;
            }
        }

        return -1;
    }

    private int[] buildNext(char[] nd) {
        int[] next = new int[nd.length];
        //j代表前缀末尾的下标，因为按定义前缀不能包含字符串的最后一个字符，所以这里初始化为0，仅当字符串总长度为2时才有意义。
        //也可以理解成，当只有一个字符时，既没有前缀，也没有后缀，所以最长相等前缀自然是0。
        //也就是说在和后缀比较时，后缀起始位置的下标至少要从1开始。所以在下面的for loop中，i就是直接从1开始的。
        int j = 0;
        next[0] = j;

        for (int i = 1; i < next.length; i++) {
            //当前缀末尾和后缀末尾不等时，往前找新的前缀末尾。如果一直不等，就一直往前找，直到找到前缀末尾下标为0为止。
            while (j > 0 && nd[j] != nd[i]) {
                j = next[j - 1];
            }

            //如果发现前缀末尾和后缀末尾相等，那么j++就表示最长相等前缀。因为下标+1就是元素的个数。
            //问：为何这里只需要判断前缀末尾和后缀末尾相等，无需比较末尾以前的字符，就能确定最长相等前缀？
            //答：因为1。我们的判断相等的顺序是从前往后的 2。我们回溯的保证也是回溯的位置之前的相等性是由最长相等前缀的定义保证的。
            if (nd[j] == nd[i]) {
                j++;
            }

            //把第i个元素的最长相等前缀记录下来
            next[i] = j;
        }
        return next;
    }

    public static void main(String[] args) {
        PrefixTableSolution pts = new PrefixTableSolution();
        String haystack = "hello";
        String needle = "ll";

        System.out.println(pts.strStr(haystack, needle));
    }
}
