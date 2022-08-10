package selfpractice.array.search.binarysearch;

import java.util.Arrays;

public class EqualSearch {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1, m;

        while (l <= r) {
            m = l + (r - l) / 2;
            if (nums[m] == target) return m;
            else if (nums[m] > target) r = m - 1;
            else l = m + 1;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[][] numses = {
                {1, 3, 5, 7, 9},
                {2, 4, 6, 8, 10},
                {4, 9}
        };

        int[] targets = {5, 6};

        EqualSearch ub = new EqualSearch();

        for (int target: targets)
            for (int[] nums : numses)
                System.out.println(String.format("nums: %s, target: %d, pos: %d", Arrays.toString(nums), target,
                        ub.search(nums, target)));
    }
}
