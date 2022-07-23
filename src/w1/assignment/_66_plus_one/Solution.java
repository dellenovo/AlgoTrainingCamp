package w1.assignment._66_plus_one;

public class Solution {
    public int[] plusOne(int[] digits) {
        if (all9(digits)) {
            int[] ret = new int[digits.length + 1];
            ret[0] = 1;
            return ret;
        }

        boolean carry = false;
        for (int i = digits.length - 1; i >= 0; i--) {
            if (carry || i == digits.length - 1) digits[i] += 1;
            if (digits[i] == 10) {
                digits[i] = 0;
                carry = true;
            } else {
                break;
            }
        }

        return digits;
    }

    boolean all9(int[] digits) {
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] != 9) return false;
        }

        return true;
    }
}
