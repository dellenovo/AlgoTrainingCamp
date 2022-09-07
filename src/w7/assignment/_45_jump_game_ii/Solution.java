package w7.assignment._45_jump_game_ii;

import java.util.Arrays;

public class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int[] f = new int[n];

        Arrays.fill(f, 20000);
        f[0] = 0;

        for (int i = 0; i < n; i++)
            for (int j = 1; j <= nums[i]; j++)
                if (i + j < n) f[i + j] = Math.min(f[i + j], f[i] + 1);

        return f[n - 1];
    }
}
