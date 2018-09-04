package com.eighteengray.algorithmlib.sort;

/**
 * 直接插入排序的基本思想是：每次从无序序列中取出第一个元素插入到已经排好序的有序序列中，从而得到一个新的，数量加1的有序序列。
 */
public class InsertSort
{
	public static void main(String[] args) {
		int[] list = {90, 10, 20, 50, 70, 40, 80, 60, 30, 52};

		System.out.println("排序后：");
		insertSort(list);
	}

	/**
	 * 直接插入排序算法
	 */
	public static void insertSort(int[] list) {
		// 从无序序列中取出第一个元素 (注意无序序列是从第二个元素开始的)
		for (int i = 1; i < list.length; i++) {
			int temp = list[i];
			int j;
			// 遍历有序序列
			// 如果有序序列中的元素比临时元素大，则将有序序列中比临时元素大的元素依次后移
			for (j = i - 1; j >= 0 && list[j] > temp; j--) {
				list[j + 1] = list[j];
			}
			// 将临时元素插入到腾出的位置中
			list[j + 1] = temp;
		}
	}

}
