package w2.assignment._47_permutations_ii;

import java.util.*;

public class Solution {
    List<List<Integer>> ans = new LinkedList<>();
    Deque<Integer> chosen = new LinkedList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        int n = nums.length;
        boolean[] used = new boolean[n];

        Arrays.sort(nums);

        permute(nums, used, 1);
        return ans;
    }

    void permute(int[] nums, boolean[] used, int seq) {
        if (seq > nums.length) {
            ans.add(new ArrayList<>(chosen));
            return;
        }

        for (int i = 0; i < used.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false) continue;
            if (used[i]) continue;
            chosen.addLast(nums[i]);
            used[i] = true;
            permute(nums, used, seq + 1);
            used[i] = false;
            chosen.removeLast();
        }
    }
}
