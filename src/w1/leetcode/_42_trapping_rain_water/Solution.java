package w1.leetcode._42_trapping_rain_water;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Solution {
    public int trap(int[] height) {
        int volume = 0;
        Deque<Rect> stack = new ArrayDeque<>();

        int[] a = new int[1];
        Arrays.toString(a);

        // 1. 遍历每个高度
        for (int h: height) {
            Rect last;
            // 2. 如果发现当前的高度比前一个高，触发调整
            while (!stack.isEmpty() && (last = stack.peek()).height < h ) {
                stack.pop();
                int accumulatedWidth = 1;
                Rect beforeLast = null;
                // 2.1 继续向前寻找高于前一个的高度，如果高度一样，则累加宽度
                while (!stack.isEmpty() && (beforeLast = stack.peek()).height <= last.height) {
                    accumulatedWidth += beforeLast.width;
                    stack.pop();
                }
                // 2.1.1 如果栈不空且找到了，则取找到的高度和当前高度的小者 * 累计宽度，计入蓄水量
                //       将前一个高度(含)和高于前一个的高度（不含）之间的矩形都出栈
                //       改为入栈一个宽度为累计宽度，高度为小者的矩形
                if (beforeLast != null) {
                    int tempHeight = Math.min(beforeLast.height, h);
                    System.out.println(String.format("\nbeforeLast %s, last: %s, current %d, delta volumne: %d", beforeLast, last, h,  accumulatedWidth * (tempHeight - last.height)));
                    volume += accumulatedWidth * (tempHeight - last.height);
                    if (accumulatedWidth * (tempHeight - last.height) > 0)
                        stack.push(new Rect(accumulatedWidth, tempHeight));
                }
            }

            // 3. 入栈当前矩阵
            stack.push(new Rect(1, h));

            System.out.println("\n visited " + h + " with volume " + volume);
            for (Rect r: stack)
                System.out.print(r + " <- ");
        }

        return volume;
    }
}

class Rect {
    int width;
    int height;
    Rect(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public String toString() {
        return String.format("(%d,%d)", width, height);
    }
}