package w9.assignment._560_subarray_sum_equals_k;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] presum = new int[n + 1];

        for (int i = 1; i <= n; i++) presum[i] = presum[i - 1] + nums[i - 1];

        Map<Integer, Integer> m = new HashMap<>();
        m.put(0, 1);
        int ans = 0;

        for (int i = 1; i <= n; i++) {
            int c = m.getOrDefault(presum[i] - k, 0);
            ans += c;
            c = m.getOrDefault(presum[i], 0);
            m.put(presum[i], c + 1);
        }

        return ans;
    }
}
