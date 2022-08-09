package w4.assignment.acwing138_find_neighbour;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        int[] nums = new int[n + 1];

        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int i = 1; i <= n; i++) {
            nums[i] = s.nextInt();
            map.put(nums[i], i);
        }

        int[] ans = new int[n + 1];

        for (int j = ans.length - 1; j > 1; j--) {
            Map.Entry<Integer,Integer> successorEntry = map.higherEntry(nums[j]);
            Map.Entry<Integer,Integer> predecessorEntry = map.lowerEntry(nums[j]);

            if (successorEntry != null && predecessorEntry != null) {
                if (successorEntry.getKey() - nums[j] < nums[j] - predecessorEntry.getKey()) {
                    ans[j] = successorEntry.getValue();
                } else if (successorEntry.getKey() - nums[j] == nums[j] - predecessorEntry.getKey()) {
                    ans[j] = successorEntry.getKey() < predecessorEntry.getKey() ? successorEntry.getValue()
                            : predecessorEntry.getValue();
                } else {
                    ans[j] = predecessorEntry.getValue();
                }
            } else if (successorEntry != null) {
                ans[j] = successorEntry.getValue();
            } else {
                ans[j] = predecessorEntry.getValue();
            }

            map.remove(nums[j]);
        }

        for (int k = 2; k < ans.length; k++) {
            System.out.println(String.format("%d %d", Math.abs(nums[k] - nums[ans[k]]), ans[k]));
        }
    }
}
