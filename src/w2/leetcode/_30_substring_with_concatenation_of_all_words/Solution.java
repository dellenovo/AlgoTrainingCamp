package w2.leetcode._30_substring_with_concatenation_of_all_words;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        // words转成word count map
        Map<String, Integer> wordCount = new HashMap<>();

        for (String word: words) {
            Integer count = wordCount.getOrDefault(word, 0);
            wordCount.put(word, count + 1);
        }

        int wordlen = words[0].length();
        int count = words.length;
        List<Integer> ans = new LinkedList<>();

        // 把s的下标对wordlen取模，因此可以分为wordlen组。每个遍历都是遍历相同组内的下标。
        for (int k = 0; k < wordlen; k ++) {
            Map<String, Integer> groupWords = new HashMap<>();
            // System.out.println("Initial:" + groupWords);
            int startWordIndex = k;
            int endWordIndex = startWordIndex + (count - 1)* wordlen;

            //结束单词的结尾已经超出了s的总长度，此时不可能再匹配了。跳到下一组。
            if (endWordIndex + wordlen > s.length()) continue;

            for (int i = startWordIndex; i  <= endWordIndex; i += wordlen) {
                addMap(groupWords, s.substring(i, i + wordlen));
                // System.out.println("First loop:" + groupWords);
            }

            while (endWordIndex <= s.length()) {
                // System.out.println("Find solution:" + groupWords);
                if (equalMap(groupWords, wordCount)) {
                    ans.add(startWordIndex);
                }

                //结束单词的结尾已经超出了s的总长度，此时不可能再匹配了。
                if (endWordIndex + wordlen + wordlen > s.length()) break;

                subtractMap(groupWords, s.substring(startWordIndex, startWordIndex + wordlen));
                startWordIndex += wordlen;
                endWordIndex += wordlen;
                addMap(groupWords, s.substring(endWordIndex, endWordIndex + wordlen));
            }
        }

        return ans;
    }

    void addMap(Map<String, Integer> map, String key) {
        if (map.containsKey(key)) {
            map.put(key, map.get(key) + 1);
        } else {
            map.put(key, 1);
        }
    }

    void subtractMap(Map<String, Integer> map, String key) {
        if (!map.containsKey(key)) {
            throw new RuntimeException(String.format("No key %s exsists in the map.", key));
        }

        int val = map.get(key);
        if (val <= 0) {
            throw new RuntimeException(String.format("The count for %s is zero, we cannot subtract from it anymore.", key));
        }

        if (val == 1) {
            map.remove(key);
        } else {
            map.put(key, val - 1);
        }
    }

    boolean equalMap(Map<String, Integer> map1, Map<String, Integer> map2) {
        if (map1.size() != map2.size()) return false;

        for (Map.Entry<String, Integer> e : map1.entrySet()) {
            if (!e.getValue().equals(map2.get(e.getKey()))) return false;
        }

        return true;
    }
}
