package w4.assignment._154_find_minimum_in_rotated_sorted_array_ii;

public class Solution {
    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1, m;

        while (l < r) {
            m = (l + r) / 2;
            // System.out.println(String.format("%d %d %d", l, m, r));
            if (nums[m] > nums[r]) l = m + 1;
            else if (nums[m] == nums[r]) r--;
            else r = m;
        }

        return nums[r];
    }
}
