package selfpractice.permutation.duplicate_elements;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    List<List<Integer>> ans;
    int[] nums;
    boolean[] used;
    Deque<Integer> chosen;
    public List<List<Integer>> permute(int[] nums) {
        ans = new LinkedList<>();
        this.nums = nums;
        used = new boolean[nums.length];
        chosen = new LinkedList<>();

        Arrays.sort(this.nums);
        permute();
        return ans;
    }

    void permute() {
        if (chosen.size() == nums.length) {
            ans.add(new LinkedList<>(chosen));
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;

            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;

            chosen.push(nums[i]);
            used[i] = true;
            permute();

            used[i] = false;
            chosen.pop();
        }
    }

    public static void main(String[] args) {
        int[][] numses = {
                {1, 2, 2},
                {0, 1},
                {1}
        };

        Solution s = new Solution();

        for (int[] nums : numses) {
            System.out.println(s.permute(nums));
        }
    }
}
