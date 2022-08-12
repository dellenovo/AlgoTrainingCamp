package w4.assignment._875_koko_eating_bananas;

public class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int l = 1, r = 0, speed;

        for (int pile : piles) {
            r += pile;
            if (r < 0) {
                r = Integer.MAX_VALUE;
                break;
            }
        }

        // System.out.println(String.format("l:%d r:%d", l, r));

        while (l < r) {
            speed = l + (r - l) / 2;
            // System.out.println(String.format("begin: l:%d r:%d speed:%d", l, r, speed));

            if (canEatUp(piles, h, speed)) {
                r = speed;
            } else {
                l = speed + 1;
            }

            // System.out.println(String.format("end: l:%d r:%d speed:%d", l, r, speed));
        }

        return r;
    }

    boolean canEatUp(int[] piles, int h, int speed) {
        int hours = 0;
        int bananas = 0;

        for (int pile : piles) {

            int th = pile / speed;
            hours += th;

            if(th * speed < pile) hours++;
        }

        boolean ret = hours <= h;
        // System.out.println(String.format("canEatUp: speed %d, ret:%s", speed, ret));
        return ret;
    }
}
