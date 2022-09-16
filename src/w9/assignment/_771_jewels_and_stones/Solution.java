package w9.assignment._771_jewels_and_stones;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        Set<Character> chars = new HashSet<>();

        for (int i = 0; i < jewels.length(); i++) chars.add(jewels.charAt(i));

        int ans = 0;

        for (int i = 0; i < stones.length(); i++)
            if (chars.contains(stones.charAt(i))) ans++;

        return ans;
    }
}
