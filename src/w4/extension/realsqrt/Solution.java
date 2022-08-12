package w4.extension.realsqrt;

public class Solution {
    public double sqrt(double x, double error) {
        double l = 0, r = x, m;

        while (r - l > error) {
            m = (l + r) / 2;

            if(m <= x / m) {
                l = m;
            } else {
                r = m;
            }
        }

        return r;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.sqrt(11, 1e-7));
    }
}
