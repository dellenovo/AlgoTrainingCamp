package w5.acwing_104_warehouse_site_selection;

import java.util.Scanner;

public class O_N_Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int len = scanner.nextInt();
        int[] nums = new int[len];

        for (int i = 0; i < len; i++) nums[i] = scanner.nextInt();

        int midVal = findMiddle(nums, 0, len - 1, len / 2);

        int sum = 0;

        for (int num : nums) {
            sum += Math.abs(num - midVal);
        }

        System.out.print(sum);
    }

    static int findMiddle(int[] nums, int left, int right, int index) {
        if (left >= right) return nums[left];

        int pivot = left + (int)(Math.random() * (right - left + 1));
        int pivotVal = nums[pivot];

        int l = left, r = right;
        while (l <= r) {
            while (nums[l] < pivotVal) l++;
            while (nums[r] > pivotVal) r--;

            if (l == r) break;

            if (l < r) {
                int t = nums[l];
                nums[l] = nums[r];
                nums[r] = t;

                l++;
                r--;
            }
        }

        if (index <= r) return findMiddle(nums, left, r, index);
        else return findMiddle(nums, r + 1, right, index);
    }
}
