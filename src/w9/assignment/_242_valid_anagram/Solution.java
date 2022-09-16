package w9.assignment._242_valid_anagram;

public class Solution {
    public boolean isAnagram(String s, String t) {
        int[] fs = getFeatureArray(s);
        int[] ft = getFeatureArray(t);

        for (int i = 0; i < fs.length; i++)
            if (fs[i] != ft[i]) return false;

        return true;
    }

    int[] getFeatureArray(String s) {
        int[] ret = new int[26];

        for (int i = 0; i < s.length(); i++)
            ret[s.charAt(i) - 'a']++;

        return ret;
    }
}
