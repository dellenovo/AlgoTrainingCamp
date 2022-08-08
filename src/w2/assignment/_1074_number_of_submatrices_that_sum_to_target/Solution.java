package w2.assignment._1074_number_of_submatrices_that_sum_to_target;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    Map<String, Integer> area = new HashMap<>();

    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int count = 0;
        int h = matrix.length;
        int w = matrix[0].length;

        // 矩阵上边界
        for (int i = 0; i < h; i++) {
            //记录这一矩阵的每个列的和
            int[] colsum = new int[w];
            //矩阵下边界
            for (int j = i; j < h; j++) {
                for (int k = 0; k < w; k++) {
                    colsum[k] += matrix[j][k];
                }

                count += subArraySum(colsum, target);
            }
        }

        return count;
    }

    int subArraySum(int[] colsum, int target) {
        Map<Integer, Integer> presums = new HashMap<>();
        presums.put(0, 1);

        int count = 0, sum = 0;

        for (int i = 0; i < colsum.length; i++) {
            sum += colsum[i];

            if (presums.containsKey(sum - target)) {
                count += presums.get(sum - target);
            }

            presums.put(sum, presums.getOrDefault(sum, 0) + 1);
        }

        return count;
    }

    int subArraySumEx2(int[] colsum, int target) {
        Map<Integer, Integer> presumCount = new HashMap<>();
        presumCount.put(0, 1);
        int sum = 0;
        int count = 0;

        for (int i = 0; i < colsum.length; i++) {
            sum += colsum[i];
            count += presumCount.getOrDefault(sum - target, 0);
            presumCount.put(sum, presumCount.getOrDefault(sum, 0) + 1);
        }

        return count;
    }
}
