package w9.assignment._438_find_all_anagrams_in_a_string;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int[] count = new int[26];

        int sLen = s.length(), pLen = p.length();

        if (sLen < pLen) return new ArrayList<Integer>();

        for (int i = 0; i < pLen; i++) {
            count[s.charAt(i) - 'a']++;
            count[p.charAt(i) - 'a']--;
        }

        int diff = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) diff++;
        }

        List<Integer> ans = new LinkedList<>();

        if (diff == 0) ans.add(0);

        for (int i = 0; i < sLen - pLen; i++) {
            int ci = s.charAt(i) - 'a';
            if (count[ci] == 1) diff--;
            else if (count[ci] == 0) diff++;

            count[ci]--;

            ci = s.charAt(i + pLen) - 'a';
            if (count[ci] == -1) diff--;
            else if (count[ci] == 0) diff++;

            count[ci]++;

            if (diff == 0) ans.add(i + 1);
        }

        return ans;
    }
}
