package selfpractice.array.sort;

import java.util.Arrays;

public class Mergesort {
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
            mergesort(a);
            System.out.println(Arrays.toString(a));
        }
    }

    public static void mergesort(int[] array) {
        mergesortEx3(array, 0, array.length);
    }

    private static void mergesort(int[] array, int start, int end) {
//        System.out.println(String.format("visit start %d, end %d", start, end));

        //这里的+1非常关键，如果没有+1，那么当end = start + 1时，算出来的m还是start,那么递归右半部分的入参就没有改变，
        // 因而会stackOverflow. 所以当end = start + 1时，就要终止了。
        if (end <= start + 1) return;
        int m = start + (end - start) / 2;

        mergesort(array, start, m);
        mergesort(array, m, end);

        int[] t = new int[end - start];
        int i = 0, j = start, k = m;
        while (j < m && k < end) {
            //这里不能写成j + i < m && k + i < end。因为j和k的移动和i没有关系，仅取决于在array[j]和array[k]的比较中是否胜出。
            //只有胜出者的指针才会后移。
            if (array[j] <= array[k]) {
                t[i++] = array[j++];
            } else {
                t[i++] = array[k++];
            }
        }

        while (j < m) {
            //记得要移动指针
            t[i++] = array[j++];
        }

        while (k < end) {
            //记得要移动指针
            t[i++] = array[k++];
        }


//        System.out.println(String.format("After visit start %d, end %d, t: %s", start, end, Arrays.toString(t)));

        System.arraycopy(t, 0, array, start, end - start);
//        System.out.println(String.format("After visit start %d, end %d, array: %s", start, end, Arrays.toString(array)));
    }

    private static void mergesortNoComment(int[] array, int start, int end) {
        if (end <= start + 1) return;
        int m = start + (end - start) / 2;

        mergesort(array, start, m);
        mergesort(array, m, end);

        int[] t = new int[end - start];
        int i = 0, j = start, k = m;
        while (j < m && k < end) {
            if (array[j] <= array[k]) {
                t[i++] = array[j++];
            } else {
                t[i++] = array[k++];
            }
        }

        while (j < m) {
            t[i++] = array[j++];
        }

        while (k < end) {
            t[i++] = array[k++];
        }

        System.arraycopy(t, 0, array, start, end - start);
    }

    private static void mergesortEx(int[] array, int start, int end) {
        if (end <= start + 1) return;

        int m = start + (end - start)/2;

        mergesortEx(array, start, m);
        mergesortEx(array, m, end);

        int[] t = new int[end - start];
        int i = 0, j = start, k = m;

        while (j < m && k < end) {
            if (array[j] <= array[k]) {
                t[i++] = array[j++];
            } else {
                t[i++] = array[k++];
            }
        }

        while (j < m) {
            t[i++] = array[j++];
        }

        while (k < end) {
            t[i++] = array[k++];
        }

        System.arraycopy(t, 0, array, start, end - start);
    }

    private static void mergesortEx2(int[] array, int start, int end) {
        if (end <= start + 1) return;

        int m = start + (end - start)/2;

        mergesortEx2(array, start, m);

        mergesortEx2(array, m, end);

        int[] t = new int[end - start];
        int i = 0, j = start, k = m;

        while (j < m && k < end) {
            if (array[j] <= array[k]) {
                t[i++] = array[j++];
            } else {
                t[i++] = array[k++];
            }
        }

        while (j < m) t[i++] = array[j++];

        while (k < end) t[i++] = array[k++];

        System.arraycopy(t, 0, array, start, end - start);
    }

    public static void mergesortEx3(int[] array, int start, int end) {
        if (end <= start + 1) return;

        int m = start + (end - start)/2;

        mergesortEx3(array, start, m);

        mergesortEx3(array, m, end);

        int[] t = new int[end - start];

        int i = 0, j = start, k = m;

        while (j < m && k < end) {
            if (array[j] <= array[k]) {
                t[i++] = array[j++];
            } else {
                t[i++] = array[k++];
            }
        }

        while (j < m) {
            t[i++] = array[j++];
        }

        while (k < end) {
            t[i++] = array[k++];
        }

        System.arraycopy(t, 0, array, start, end - start);
    }
}
