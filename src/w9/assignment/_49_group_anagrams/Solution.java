package w9.assignment._49_group_anagrams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> m = new HashMap<>();

        for (String str : strs) {
            String key = calcKey(str);
            m.putIfAbsent(key, new ArrayList<>());
            m.get(key).add(str);
        }

        return new ArrayList(m.values());
    }

    String calcKey(String s) {
        int[] count = new int[26];

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < count.length; i++)
            if (count[i] > 0) {
                sb.append((char)('a' + i));
                sb.append(count[i]);
            }

        return sb.toString();
    }
}
