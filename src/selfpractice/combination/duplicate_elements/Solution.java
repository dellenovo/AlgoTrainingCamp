package selfpractice.combination.duplicate_elements;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    List<List<Integer>> ans;
    int[] nums;
    int k;
    boolean[] used;
    Deque<Integer> chosen;
    public List<List<Integer>> combine(int[] nums, int k) {
       ans = new LinkedList<>();
       this.nums = nums;
       this.k = k;
       used = new boolean[nums.length];
       chosen = new LinkedList<>();

        Arrays.sort(this.nums);
       combine(0);
       return ans;
    }

    void combine(int index) {
        if (chosen.size() == k || chosen.size() + nums.length - index < k) {
            if (chosen.size() == k) ans.add(new LinkedList<>(chosen));
            return;
        }

        if (index > 0 && nums[index] == nums[index - 1] && !used[index - 1]) return;

        combine(index + 1);
        chosen.push(nums[index]);
        used[index] = true;
        combine(index + 1);
        used[index] = false;
        chosen.pop();
    }

    public static void main(String[] args) {
        int[][] numses = {
                {1, 2, 2},
                {0, 1},
                {1}
        };

        Solution s = new Solution();

        for (int[] nums : numses) {
            System.out.println(s.combine(nums, 2));
        }
    }
}
