package w7.assignment._55_jump_game;

public class Solution {
    public boolean canJump(int[] nums) {
        int n = nums.length;

        boolean[] f = new boolean[n];
        f[0] = true;

        for (int i = 0; i < n; i++)
            for (int j = 1; j <= nums[i]; j++)
                if (i + j < n) f[i + j] = f[i];

        return f[n - 1];
    }
}
