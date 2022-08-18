package w5.assignment._321_count_of_range_sum;

public class Solution {
    long[] prefix;
    int count;
    int lower, upper;

    public int countRangeSum(int[] nums, int lower, int upper) {
        this.prefix = new long[nums.length + 1];

        for (int i = 1; i < prefix.length; i++) {
            prefix[i] += prefix[i - 1] + nums[i - 1];
        }

        this.lower = lower;
        this.upper = upper;
        count = 0;

        mergesort(0, prefix.length - 1);

        return count;
    }

    void mergesort(int left, int right) {
        if (left >= right) return;

        int m = (left + right) >> 1;

        mergesort(left, m);
        mergesort(m + 1, right);

        calcSumCount(left, m, right);

        int i = left, j = m + 1;
        long[] temp = new long[right - left + 1];

        for (int k = 0; k < temp.length; k++) {
            if (j > right || (i <= m && prefix[i] <= prefix[j])) {
                temp[k] = prefix[i++];
            } else {
                temp[k] = prefix[j++];
            }
        }

        System.arraycopy(temp, 0, prefix, left, temp.length);
    }

    void calcSumCount(int l, int m, int r) {
        int left = m + 1, right = m + 1;
        for (int i = l; i <= m; i++) {
            while (left <= r && prefix[left] - prefix[i] < lower) left++;
            while(right <= r && prefix[right] - prefix[i] <= upper) right++;

            count += right - left;
        }
    }
}
