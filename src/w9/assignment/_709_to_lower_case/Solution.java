package w9.assignment._709_to_lower_case;

public class Solution {
    public String toLowerCase(String s) {
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++)
            if (chars[i] >= 'A' && chars[i] <= 'Z')
                chars[i] += 'a' - 'A';
        return new String(chars);
    }
}
