package w6.assignment._673_number_of_LIS;

import java.util.Arrays;

public class Solution {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        // the length of the max increasing subsequences ending with the ith number
        int[] f = new int[n];
        Arrays.fill(f, 1);

        int[] count = new int[n];
        Arrays.fill(count, 1);

        int maxLen = 1, maxCount = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (f[j] + 1 == f[i]) {
                        count[i] += count[j];
                    } else if (f[j] + 1 > f[i]) {
                        f[i] = f[j] + 1;
                        count[i] = count[j];
                    }
                }
            }

            if (f[i] == maxLen) maxCount += count[i];
            else if (f[i] > maxLen) {
                maxLen = f[i];
                maxCount = count[i];
            }
        }

        return maxCount;
    }
}
