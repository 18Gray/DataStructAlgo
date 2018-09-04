package com.eighteengray.algorithmlib.sort;

/**
 *
 */
class ShellSort {
    public static void main(String[] args) {
        int[] list = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

        System.out.println("排序后：");
        shellSort(list);
    }

    /**
     * 希尔排序算法
     */
    public static void shellSort(int[] list) {
        // 取增量
        int gap = list.length / 2;

        while (gap >= 1) {
            // 无序序列
            for (int i = gap; i < list.length; i++) {
                int temp = list[i];
                int j;

                // 有序序列
                for (j = i - gap; j >= 0 && list[j] > temp; j = j - gap) {
                    list[j + gap] = list[j];
                }
                list[j + gap] = temp;
            }

            // 缩小增量
            gap = gap / 2;
        }
    }

}
