package w9.assignment._205_isomorphic_strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
    public boolean isIsomorphic(String s, String t) {
        return isSingleReflect(s, t) && isSingleReflect(t, s);
    }

    boolean isSingleReflect(String from, String to) {
        if (from.length() != to.length()) return false;

        // character in to set -> character in from set
        Map<Character, Character> reverseMap = new HashMap<>();
        Set<Character> toset = new HashSet<>();

        for (int i = 0; i < from.length(); i++) {
            char fc = from.charAt(i);
            char tc = to.charAt(i);
            if (toset.contains(tc) && reverseMap.get(tc) != fc) return false;
            toset.add(tc);
            reverseMap.put(tc, fc);
        }

        return true;
    }
}
