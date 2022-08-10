package selfpractice.array.search.binarysearch;

import java.util.Arrays;

public class LowerBound {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length;

        while (l < r) {
            int m = (l + r) / 2;

            // with equal: including element equal to target
//            if (nums[m] >= target) {
            // without equal: excluding element equal to target
            if (nums[m] > target) {
                r = m;
            } else {
                l = m + 1;
            }
        }

        return r;
    }

    public int searchSuccessor(int[] nums, int target) {
        int l = 0, r = nums.length, m;

        while (l < r) {
            m = (l + r) / 2;
            if (nums[m] >= target) {
                r = m;
            } else {
                l = m + 1;
            }
        }

        return r;
    }

    public static void main(String[] args) {
        int[][] numses = {
                {1, 3, 5, 7, 9},
                {2, 4, 6, 8, 10}
        };

        int[] targets = {5, 6};

        LowerBound lb = new LowerBound();

        for (int target: targets)
            for (int[] nums : numses)
                System.out.println(String.format("nums: %s, target: %d, pos: %d", Arrays.toString(nums), target,
                        lb.searchSuccessor(nums, target)));
    }
}
