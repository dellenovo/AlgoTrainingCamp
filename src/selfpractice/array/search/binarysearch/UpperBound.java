package selfpractice.array.search.binarysearch;

import java.util.Arrays;

public class UpperBound {
    public int searchUpperBound(int[] nums, int target) {
        int l = -1, r = nums.length - 1, m;

        while (l < r) {
            m = (l + r + 1) / 2;
//            System.out.println(String.format("l: %d, r:%d, m:%d", l, r, m));

            if (nums[m] <= target) {
                l = m;
            } else {
                r = m - 1;
            }
        }

        return r;
    }

    public int searchPredecessorEx(int[] nums, int target) {
        int l = -1, r = nums.length - 1, m;

        while (l < r ) {
            m = (l + r + 1) >> 1;
//            System.out.println(String.format("l: %d, r:%d, m:%d", l, r, m));
            if (nums[m] <= target) {
                l = m;
            } else {
                r = m - 1;
            }
        }

        return r;
    }

    public static void main(String[] args) {
        int[][] numses = {
                {1, 3, 5, 7, 9},
                {2, 4, 6, 8, 10},
                {4, 9}
        };

        int[] targets = {5, 6};

        UpperBound ub = new UpperBound();

        for (int target: targets)
            for (int[] nums : numses)
                System.out.println(String.format("nums: %s, target: %d, pos: %d", Arrays.toString(nums), target,
                        ub.searchPredecessorEx(nums, target)));
    }
}
