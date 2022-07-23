package w2.leetcode._49_group_anagrams;

import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> anagrams = new HashMap<>();

        for (String str: strs) {
            System.out.println("visit " + str);
            String key = calcKey(str);
            System.out.println("key is " + key);

            anagrams.putIfAbsent(key, new ArrayList<String>());

            anagrams.get(key).add(str);

            System.out.println("anagrams size:" + anagrams.size());
        }

        return new ArrayList<>(anagrams.values());
    }

    String calcKey(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }

    public static void main(String[] args) {
        new Solution().groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"});
    }
}