package selfpractice.subset.leetcode._78_subsets;

import java.util.*;

public class Solution {
    int[] nums;
    Set<Integer> chosen;
    List<List<Integer>> ans;
    public List<List<Integer>> subsets(int[] nums) {
        this.nums = nums;
        chosen = new HashSet<>();
        ans = new LinkedList<>();
        subsets(0);
        return ans;
    }

    void subsets(int seq) {
        if (seq >= nums.length) {
            ans.add(new LinkedList<>(chosen));
            return;
        }

        subsets(seq + 1);
        chosen.add(nums[seq]);
        subsets(seq + 1);
        chosen.remove(nums[seq]);
    }

    public static void main(String[] args) {
        int[][] numses = {{1, 2, 3},
                {0}};

        Solution s = new Solution();

        for (int[] nums: numses) {
            List<List<Integer>> ans = s.subsets(nums);
            System.out.println(ans);
        }
    }
}
