package w4.assignment._74_search_a_2d_matrix;

public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int l = 0, m = matrix.length, n = matrix[0].length, mid;

        int r = m * n - 1;

        while (l <= r) {
            mid = (l + r + 1) / 2;

            int x = mid / n, y = mid % n;

            if (matrix[x][y] == target) return true;
            if (matrix[x][y] > target) r = mid - 1;
            else {
                l = mid + 1;
            }
        }

        return false;
    }
}
