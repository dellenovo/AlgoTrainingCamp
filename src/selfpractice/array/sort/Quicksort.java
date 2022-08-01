package selfpractice.array.sort;

import java.util.Arrays;

public class Quicksort {
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
            quicksort(a);
            System.out.println(Arrays.toString(a));
        }
    }

    public static void quicksort(int[] array) {
//        quicksort(array, 0, array.length);
        quicksortEx2(array, 0, array.length);
    }

    private static void quicksort(int[] array, int start, int end) {
        //对于end = start + 1的情况，即待排序的元素只有一个时，虽然其实无需排序，end <= start的终止条件不能马上终止，
        //但是不会进入后面的循环体，递归时，仍将出现end == start的情况。即递归[start, start), [start + 1, start + 1)
        //在下一层会被终止的。
        //这里为了和归并排序一致，减少记忆负担，改为end <= start + 1
        if (end <= start + 1 ) return;

        int left = start, right = end - 1;

        //把第一个元素作为中心点并记录
        int pivot = array[start];

        while (left < right) {
            // 从右边开始找比中心点小的数，一开始left就是标记点本尊。即使当right减少到left时，由于比较条件是>, 而不是>=，所以right不
            // 可能再减少到比left小。
            // 或者出于和右移左指针保持一致减轻记忆负担的角度，可以把条件改为left < right && array[right] >= pivot
            // 这里取后者。
            while (left < right && array[right] >= pivot) right--;

            // 因为不可能出现left < right这种情况，所以注释掉。
//            if (left < right) {
//            swap(array, left, right);

            // 由于已经把中心点备份到了pivot,并且中心点最终是位于left与right相遇的地方，所以前几次的交换其实是没有必要的。
            // 故而这里改成把找到的小于中心点的right位置的元素直接赋值到left的位置。不用担心right位置的元素还在那里，因为
            // 在下面移动left指针时，同样也会把找到的大于中心点的left位置的元素赋值到right位置。那left位置的元素还是重复了，
            // 怎么办？不要紧，在left和right指针相遇后，会把pivot赋值给left。这也是pivot的最终归宿。
            array[left] = array[right];
//            }
            //当两指针相等时，后面right指针左移也不会发生，所以不用额外break.
//            else if (left == right) {
//                break;
//            }

            //right 不可能小于 left，因为当right == left时，不满足array[right] > array[left]，所以right不会再自减了。
            //由于上面已经把left 和 right对调了，所以此时right是标记点。继续在标记点左边找比标记点大的数。
            //如果只是比较array[left] < array[right]的话，那么left同样不可能超过right.
            //但如果存在和标记点同值的点，由于不会满足两个while种的条件，所以left & right指针都不再移动，导致程序会在这两个相同数值
            // 的位置反复交换陷入死循环。所以这里不能写成<，而要写成<=
            //那这样就会出现left>right,甚至数组越界，所以条件还要加上left < right
            while (left < right && array[left] <= pivot) left++;

            //由于上面while中的left < right的逻辑，所以left不可能大于right。等于是可能的，但没有什么严重后果，只是多了一次原地
            // 自己赋值给自己，无伤大雅。所以if判断可以去掉。
//            if (left < right) {
//            swap(array, left, right);
            array[right] = array[left];
//            }
            //当两指针相等时，直接依靠外层循环判断条件就能跳出循环了，这里不需要额外break。
//            else if (left == right){
//                break;
//            }
        }

        array[left] = pivot;

        //不要忘记递归，并且分界点传的应该是left,不要错误的传成了pivot了。
        quicksort(array, start, left);
        quicksort(array, left + 1, end);
    }

    private static void quicksortNoComment(int[] array, int start, int end) {
        if (end <= start) return;

        int left = start, right = end - 1;

        int pivot = array[start];

        while (left < right) {
            while (left < right && array[right] >= pivot) right--;

            array[left] = array[right];

            while (left < right && array[left] <= pivot) left++;

            array[right] = array[left];
        }

        array[left] = pivot;

        quicksortNoComment(array, start, left);
        quicksortNoComment(array, left + 1, end);
    }

    private static void quicksortEx(int[] array, int start, int end) {
        if (end <= start) return;

        int low = start, high = end - 1;

        int pivot = array[start];

        while (low < high) {
            while (low < high && array[high] >= pivot) high--;

            array[low] = array[high];

            while (low < high && array[low] <= pivot) low++;

            array[high] = array[low];
        }

        array[low] = pivot;

        quicksortEx(array, start, low);
        quicksortEx(array, low + 1, end);
    }

    private static void quicksortEx2(int[] array, int start, int end) {
        if (start + 1 >= end) return;

        int pivot = array[start];

        int l = start, r = end - 1;

        while (l < r) {
            while (l < r && array[r] >= pivot) r--;
            array[l] = array[r];

            while (l < r && array[l] <= pivot) l++;
            array[r] = array[l];
        }
        array[l] = pivot;

        quicksortEx2(array, start, l);
        quicksortEx2(array, l + 1, end);
    }

}
