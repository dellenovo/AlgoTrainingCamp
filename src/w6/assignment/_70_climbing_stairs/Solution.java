package w6.assignment._70_climbing_stairs;

public class Solution {
    public int climbStairs(int n) {
        int old = 1, cur = 1, temp = -1;

        for (int i = 2; i <= n; i++) {
            temp = cur + old;
            old = cur;
            cur = temp;
        }

        return cur;
    }
}
