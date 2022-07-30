package selfpractice.array.sort;

import java.util.Arrays;

public class Selectsort {
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
            selectsortEx2(a);
            System.out.println(Arrays.toString(a));
        }
    }

    public static void selectsort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            //注意minVal的初始值就选择本次循环开始的首个元素,并且在每次外层循环开始时都要初始化。
            //如果首个元素刚好就是最小元素，也没关系下面的循环体找不到比首元素小的数，于是minIndex不变，后面就是自己和自己交换一下。
            int minVal = array[i], minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < minVal) {
                    minVal = array[j];
                    minIndex = j;
                }
            }

            array[minIndex] = array[i];
            array[i] = minVal;
        }
    }

    public static void selectsortNoComment(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minVal = array[i], minIndex = i;

            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < minVal) {
                    minIndex = j;
                    minVal = array[j];
                }
            }

            array[minIndex] = array[i];
            array[i] = minVal;
        }
    }

    public static void selectsortEx(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minVal = array[i], minIndex = i;

            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < minVal) {
                    minIndex = j;
                    minVal = array[j];
                }
            }

            array[minIndex] = array[i];
            array[i] = minVal;
        }
    }

    public static void selectsortEx2(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minVal = array[i], minIndex = i;

            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < minVal) {
                    minIndex = j;
                    minVal = array[j];
                }
            }

            array[minIndex] = array[i];
            array[i] = minVal;
        }
    }
}
