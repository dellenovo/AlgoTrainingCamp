package w10.assignment._239_sliding_window_maximum;

import java.util.TreeMap;

public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        //TreeMap
        int[] ans = new int[nums.length - k + 1];

        TreeMap<Integer, Integer> map = new TreeMap<>();

        for(int i = 0; i < nums.length; i++) {
            // System.out.println(map);
            int count = map.getOrDefault(nums[i], 0);
            map.put(nums[i], ++count);
            //先删除移出的元素
            if (i > k - 1) {
                count = map.get(nums[i - k]);
                count--;
                if (count <= 0) map.remove(nums[i - k]);
                else map.put(nums[i - k], count);
            }
            //再取最大值
            if (i >= k - 1) {
                ans[i - k + 1] = map.lastKey();
            }
        }

        return ans;
    }
}
