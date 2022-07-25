package w2.assignment._697_degree_of_an_array;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, Stat> stats = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (stats.containsKey(nums[i])) {
                Stat c = stats.get(nums[i]);
                c.count ++;
                c.end = i;
            } else {
                Stat n = new Stat(i);
                stats.put(nums[i], n);
            }
        }

        Stat max = stats.entrySet().stream().max((e1, e2) -> {
            int countdiff = e1.getValue().count - e2.getValue().count;
            if (countdiff != 0) return countdiff;
            return e2.getValue().end - e2.getValue().start - e1.getValue().end + e1.getValue().start;
        }).get().getValue();

        return max.end - max.start + 1;
    }
}
class Stat {
    int count = 0;
    int start;
    int end;
    Stat(int start) {
        this.start = start;
        this.end = start;
    }
}