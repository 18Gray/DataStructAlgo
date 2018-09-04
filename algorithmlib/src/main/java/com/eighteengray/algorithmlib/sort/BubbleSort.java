package com.eighteengray.algorithmlib.sort;



/**
 * 通过相邻两个元素之间的比较和交换，使较大的元素逐渐从前面移向后面（升序），就像水底下的气泡一样逐渐向上冒泡，所以被称为“冒泡”排序。
 * 算法复杂度分析：使用优化后的冒泡排序，最好的情况下，仅需要n - 1次比较，时间复杂度为O(n)；最坏情况下，需要n(n - 1)/2次比较和交换；
 所以平均时间复杂度为O(n2)。
 */
public class BubbleSort
{
	public static void main(String[] args) {
		int[] list = {36, 28, 45, 13, 67, 37, 18, 56};
		System.out.println("排序后：");
		bubbleSort(list);
	}

	/**
	 * 冒泡排序算法
	 */
	public static void bubbleSort(int[] list) {
		int temp;
		// 做多少轮排序（最多length-1轮）
		for (int i = 0; i < list.length - 1; i++) {
			// 每一轮比较多少个
			for (int j = 0; j < list.length - 1 - i; j++) {
				if (list[j] > list[j + 1]) {
					// 交换次序
					temp = list[j];
					list[j] = list[j + 1];
					list[j + 1] = temp;
				}
			}
		}
	}
	
}