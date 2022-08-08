package selfpractice.permutation.leetcode._46_permutations;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    List<List<Integer>> ans;
    int[] nums;
    boolean[] used;
    // 此处可不能用Set<Integer>, 因为全排列需要结果的顺序，选择set那么顺序都会变得一样。
    Deque<Integer> chosen;
    public List<List<Integer>> permute(int[] nums) {
        ans = new LinkedList<>();
        this.nums = nums;
        used = new boolean[nums.length];
        chosen = new LinkedList<>();

        permute();
        return ans;
    }

    void permute() {
        if (chosen.size() == nums.length) {
            ans.add(new LinkedList<>(chosen));
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;

            used[i] = true;
            chosen.push(nums[i]);
            permute();
            used[i] = false;
            chosen.pop();
        }
    }

    public static void main(String[] args) {
        int[][] numses = {
                {1, 2, 3},
                {0, 1},
                {1}
        };

        Solution s = new Solution();

        for (int[] nums : numses) {
            System.out.println(s.permute(nums));
        }
    }
}
