package w5.assignment._17_letter_combinations_of_a_phone_number;

import java.util.*;

public class Solution {
    //17. Letter Combinations of a Phone Number
    List<String> ans;

    Map<Integer, List<Character>> numLetters = Map.of(
            2, List.of('a','b','c'),
            3, List.of('d','e','f'),
            4, List.of('g','h','i'),
            5, List.of('j','k','l'),
            6, List.of('m','n','o'),
            7, List.of('p','q','r', 's'),
            8, List.of('t','u','v'),
            9, List.of('w','x','y', 'z')
    );
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) return Collections.EMPTY_LIST;
        int[] digs = new int[digits.length()];

        for (int i = 0; i < digits.length(); i++)
            digs[i] = Integer.parseInt(digits, i, i + 1, 10);

        ans = new LinkedList<>();

        Deque<Character> s = new LinkedList<>();

        dfs(digs, s);

        return ans;
    }

    void dfs(int[] digs, Deque<Character> s) {
        if (s.size() >= digs.length) {
            ans.add(buildAnswer(s));
            return;
        }

        for (char letter : numLetters.get(digs[s.size()])) {
            s.push(letter);
            dfs(digs, s);
            s.pop();
        }

    }

    String buildAnswer(Deque<Character> s) {
        StringBuilder sb = new StringBuilder();
        for (Iterator<Character> di = s.descendingIterator(); di.hasNext();) {
            sb.append(di.next());
        }

        return sb.toString();
    }
}
