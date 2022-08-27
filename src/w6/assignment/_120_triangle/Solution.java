package w6.assignment._120_triangle;

import java.util.Collections;
import java.util.List;

public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int depth = triangle.size();

        for (int i = 1; i < depth; i++)
            for (int j = 0; j < i + 1; j++)
                if (j == 0) triangle.get(i).set(j, triangle.get(i).get(j) + triangle.get(i - 1).get(j));
                else if (j == i) triangle.get(i).set(j, triangle.get(i).get(j) + triangle.get(i - 1).get(j - 1));
                else triangle.get(i).set(j,
                            Math.min(
                                    triangle.get(i).get(j) + triangle.get(i - 1).get(j - 1),
                                    triangle.get(i).get(j) + triangle.get(i - 1).get(j)));

        return Collections.min(triangle.get(depth - 1));
    }
}
