package w4.assignment._1011_capacity_to_ship_packages_within_d_days;

public class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int l = 0, r = 0, cap;

        for (int weight : weights) {
            l = Math.max(l, weight);
            r += weight;
        }

        while (l < r) {
            cap = (l + r) / 2;
            if (canShipWithinDays(weights, days, cap)) {
                r = cap;
            } else {
                l = cap + 1;
            }
        }

        return r;
    }

    boolean canShipWithinDays(int[] weights, int days, int cap) {
        int vol = 0, daycount = 1;

        for (int weight: weights) {
            if (vol + weight <= cap) {
                vol += weight;
            } else {
                vol = weight;
                daycount++;
            }
        }

        return daycount <= days;
    }
}
