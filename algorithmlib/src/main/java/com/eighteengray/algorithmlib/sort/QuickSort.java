package com.eighteengray.algorithmlib.sort;

/**
 * 快速排序(Quick Sort) 是对冒泡排序的一种改进方法，在冒泡排序中，进行元素的比较和交换是在相邻元素之间进行的，元素每次交换只能移动一个位置，所以比较次数和移动次数较多，效率相对较低。而在快速排序中，元素的比较和交换是从两端向中间进行的，较大的元素一轮就能够交换到后面的位置，而较小的元素一轮就能交换到前面的位置，元素每次移动的距离较远，所以比较次数和移动次数较少，速度较快，故称为“快速排序”。
 基本思想是：通过一轮排序将待排序元素分割成独立的两部分， 其中一部分的所有元素均比另一部分的所有元素小，然后分别对这两部分的元素继续进行快速排序，以此达到整个序列变成有序序列。

 复杂度分析：最好情况：partition每次划分的都很均匀，如果排序n个关键字，其递归树的深度就为floor(log2n)+ 1次，此时的复杂度为O(nlogn)。
 如果是最坏情况，每次partition都只操作一个数字，该递归树即为一颗斜树，比较次数为n(n - 1)/2，时间复杂度为O(n2)。
 平均复杂度为O(nlogn)。
 */
class QuickSort {
    public static void main(String[] args) {
        int[] list = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        quickSort(list, 0, list.length - 1);
    }

    /**
     * 快速排序算法
     */
    public static void quickSort(int[] list, int left, int right) {
        if (left < right) {
            // 分割数组，找到分割点
            int point = partition(list, left, right);

            // 递归调用，对左子数组进行快速排序
            quickSort(list, left, point - 1);
            // 递归调用，对右子数组进行快速排序
            quickSort(list, point + 1, right);
        }
    }

    /**
     * 分割数组，找到分割点
     */
    public static int partition(int[] list, int left, int right) {
        // 用数组的第一个元素作为基准数
        int first = list[left];
        while (left < right) {
            while (left < right && list[right] >= first) {
                right--;
            }
            // 交换
            swap(list, left, right);

            while (left < right && list[left] <= first) {
                left++;
            }
            // 交换
            swap(list, left, right);
        }
        // 返回分割点所在的位置
        return left;
    }

    /**
     * 交换数组中两个位置的元素
     */
    public static void swap(int[] list, int left, int right) {
        CommonUtil.swap(list, left, right);
    }

}
