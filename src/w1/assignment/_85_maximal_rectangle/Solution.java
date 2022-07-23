package w1.assignment._85_maximal_rectangle;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    public int maximalRectangle(char[][] matrix) {
        int rows = matrix.length;
        int maxArea = 0;

        // 分解成从第0行到第r行的场景, 将每一个场景转成一维数组
        int[][] scenes = parseScenes(matrix);

        // 对每一个一维数组用单调栈的方式来算出最大矩形面积
        for (int i = 0; i < rows; i++) {
            maxArea = Math.max(maxArea, getMaxRect(scenes[i]));
        }

        return maxArea;
    }

    int[][] parseScenes(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] scenes = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0) {
                    scenes[i][j] = matrix[i][j] - '0';
                } else {
                    scenes[i][j] = (matrix[i][j] == '1') ? (1 + scenes[i - 1][j]) : 0;
                }
            }
        }

        return scenes;
    }

    int getMaxRect(int[] scene) {
        int DUMMY_START = -1;
        int maxArea = 0;
        Deque<Integer> indexStack = new ArrayDeque<>();

        // 最后加入0，以使得所有之前的下标都能在后面的循环中出栈
        int[] sceneEndingWith0 = new int[scene.length + 1];
        System.arraycopy(scene, 0, sceneEndingWith0, 0, scene.length);

        for (int curIndex = 0; curIndex < sceneEndingWith0.length; curIndex++) {
            int h = sceneEndingWith0[curIndex];
            // 此时需要调整栈以维护单调递增性
            while (!indexStack.isEmpty() && h < sceneEndingWith0[indexStack.peek()]) {
                int lastIndex = indexStack.pop();
                int beforeLastIndex = indexStack.isEmpty() ? DUMMY_START : indexStack.peek();
                int maxAreaCandidate = sceneEndingWith0[lastIndex] * (curIndex - beforeLastIndex - 1);
                maxArea = Math.max(maxArea, maxAreaCandidate);
            }
            indexStack.push(curIndex);
        }

        return maxArea;
    }
}