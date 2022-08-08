package w2.assignment._47_permutations_ii;

import java.util.*;

public class Solution {
    List<List<Integer>> ans = new LinkedList<>();
    Deque<Integer> chosen = new LinkedList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        int n = nums.length;
        boolean[] used = new boolean[n];

        Arrays.sort(nums);

//        permute(nums, used, 1);
        permuteEx2(nums, used, 1);
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
            // 这里不需要考虑不加元素的情况，因为本来排列就需要包含所有元素。所以每次都必须选择一个元素。
            chosen.addLast(nums[i]);
            used[i] = true;
            permute(nums, used, seq + 1);
            used[i] = false;
            chosen.removeLast();
        }
    }

    void permuteEx2(int[] nums, boolean[] used, int seq) {
        if (seq > nums.length) {
            ans.add(new ArrayList<>(chosen));
            return;
        }

        for (int i = seq - 1; i < used.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            if (used[i]) continue;
            chosen.addLast(nums[i]);
            used[i] = true;
            permute(nums, used, seq + 1);
            used[i] = false;
            chosen.removeLast();
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 2, 3};
        List<List<Integer>> ans = solution.permuteUnique(nums);
        for (List<Integer> a : ans) {
            System.out.println(a);
        }
    }
}
