package w2.assignment._1074_number_of_submatrices_that_sum_to_target;

public class ExceedTimeLimitSolution {
    int count = 0;
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        count = 0;
        int h = matrix.length;
        int w = matrix[0].length;
        int x1 = 0, x2 = 0, y1 = 0, y2 = 0;

        // System.out.println(String.format("h: %d, w: %d", h, w));

        int sum = 0;

        boolean canRightExpand = true, canDownExpand = true;
        int rightDownX = x2, rightDownY = y2;

        for (;x1 < w && x2 < w && y1 < h && y2 < h;) {
            // System.out.println(String.format("visit (%d, %d) - (%d, %d)", x1, y1, x2, y2));
            // 初始
            isQualified(matrix, target, x1, x2, y1, y2);

            // 向右扩展
            if (canRightExpand && x2 < w - 1 && y2 < h) {
                x2++;
                // System.out.println(String.format("to right (%d, %d) - (%d, %d)", x1, y1, x2, y2));
                continue;
            }

            x2 = rightDownX;
            canRightExpand = false;

            // 向下扩展
            if (canDownExpand && y2 < h - 1) {
                y2++;
                // System.out.println(String.format("to down (%d, %d) - (%d, %d)", x1, y1, x2, y2));
                continue;
            }

            y2 = rightDownY;
            canDownExpand = false;

            // 扩大
            if (rightDownX < w - 1 && rightDownY < h - 1) {
                rightDownX++;
                rightDownY++;
                x2 = rightDownX;
                y2 = rightDownY;
                canRightExpand = true;
                canDownExpand = true;
                // System.out.println(String.format("expand (%d, %d) - (%d, %d)", x1, y1, x2, y2));
                continue;
            }

            //左上角为(x1, y1)的矩阵已全部遍历完毕，遍历下一个点
            if (x1 < w - 1) {
                x1++;
                x2 = x1;
                y2 = y1;
                rightDownX = x2;
                rightDownY = y2;
                canRightExpand = true;
                canDownExpand = true;
            } else if (x1 == w - 1 && y1 < h - 1) {
                x1 = 0;
                x2 = 0;
                y1++;
                y2 = y1;
                rightDownX = x2;
                rightDownY = y2;
                canRightExpand = true;
                canDownExpand = true;
            } else {
                //遍历完所有的点
                break;
            }
        }

        return count;
    }

    void isQualified(int[][] matrix, int target, int x1, int x2, int y1, int y2) {
        int sum = sumMatrix(matrix, x1, x2, y1, y2);

        if (sum == target) count++;
    }

    int sumMatrix(int[][] matrix, int x1, int x2, int y1, int y2) {
        int sum = 0;
        for (int i = x1, j = y1; i <= x2 && j <= y2;) {
            sum += matrix[j][i];
            i++;
            if (i > x2 && j < y2) {
                i = x1;
                j++;
            }
        }

        return sum;
    }
}
