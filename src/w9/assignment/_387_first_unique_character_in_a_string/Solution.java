package w9.assignment._387_first_unique_character_in_a_string;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int firstUniqChar(String s) {
        Map<Character, Integer> count = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int cc = count.getOrDefault(c, 0);
            count.put(c, cc + 1);
        }

        for (int i = 0; i < s.length(); i++)
            if (count.get(s.charAt(i)) == 1) return i;

        return -1;
    }
}
