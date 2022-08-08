package selfpractice.array.sort;

import java.util.Arrays;

public class Heapsort {
    public static void main(String[] args) {
        int[][]as = {
                {1, 6, 3, 4, 5, 2},
                {1, 6, 3, 4, 5, 2, 3, 4},
                {},
                {1, 2, 3},
                {3, 2, 1},
                {1}
        };

        for (int[] a: as){
            heapsort(a);
            System.out.println(Arrays.toString(a));
        }
    }

    private static void heapsort(int[] a) {
        int len = a.length;
        // 总记得堆排序的建堆和排序时用的调整算法是反的。没想到也是可以一致的。
        for (int i = len / 2; i >= 0; i--) {
            adjustDown(a, i, len);
        }

        for (int i = len - 1; i > 0; i--) {
            swap(a, 0, i);
            adjustDown(a, 0, i);
        }
    }

    private static void adjustDown(int[] a, int i, int limit) {
        int child;

        while ((child = i * 2 + 1) < limit) {
            if (child + 1 < limit && a[child + 1] > a[child]) child++;
            if (a[i] < a[child]) swap(a, i, child);
            else break;
            i = child;
        }
    }

//    private static void adjustUp(int[] a, int i) {
//        int p;
//        while (i > 0) {
//            p = (i - 1) / 2;
//            if (a[p] < a[i]) swap(a, p, i);
//            i = p;
//        }
//    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
