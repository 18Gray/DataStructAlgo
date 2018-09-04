package com.eighteengray.algorithmlib.sort;


/**
 * 直接选择排序(Straight Select Sort) 是一种简单的排序方法，它的基本思想是：通过length-1 趟元素之间的比较，从length-i+1个元素中选出最小的元素，并和第i个元素交换位置。
 * 直接选择排序的最坏时间复杂度为O(n2)，平均时间复杂度为O(n2)
 */
public class SelectSort
{
	public static void main(String[] args) {
		int[] list = {9, 1, 2, 5, 7, 4, 8, 6, 3, 5};

		System.out.println("排序后：");
		selectionSort(list);
	}

	/**
	 * 直接选择排序算法
	 */
	public static void selectionSort(int[] list) {
		// 要遍历的次数（length-1次）
		for (int i = 0; i < list.length - 1; i++) {
			// 将当前下标定义为最小值下标
			int min = i;

			// 遍历min后面的数据
			for (int j = i + 1; j <= list.length - 1; j++) {
				// 如果有小于当前最小值的元素，将它的下标赋值给min
				if (list[j] < list[min]) {
					min = j;
				}
			}
			// 如果min不等于i，说明找到真正的最小值
			if (min != i) {
				swap(list, min, i);
			}
		}
	}

	/**
	 * 交换数组中两个位置的元素
	 */
	public static void swap(int[] list, int min, int i) {
		CommonUtil.swap(list, min, i);
	}

}