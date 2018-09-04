package com.eighteengray.algorithmlib.sort;


/**
 * 归并排序是建立在归并操作上的一种有效的排序算法，该算法是采用分治法的典型应用。
 它指的是将两个已经排序的序列合并成一个序列的操作。归并排序算法依赖归并操作。归并排序有多路归并排序、两路归并排序 , 可用于内排序，也可以用于外排序。这里仅对内排序的两路归并方法进行讨论。

 算法思路

 把 n 个记录看成 n 个长度为 l 的有序子表
 进行两两归并使记录关键字有序，得到 n/2 个长度为 2 的有序子表
 重复第 2 步直到所有记录归并成一个长度为 n 的有序表为止。
 算法复杂度分析：
 在最后一步，需要依次遍历两个已排序的好的数组，此时的时间复杂度为O(n)。
 同时又进行着二路归并，形成一颗完全二叉树，此时整个排序需要进行log2n次。
 所以归并排序的时间复杂度为O(nlogn)。这是它的最好、最坏、平均的时间性能。
 */

class MergeSort {
	public static void main(String[] args) {
		int[] list = {50, 10, 90, 30, 70};

		mergeSort(list, new int[list.length], 0, list.length - 1);
	}

	/**
	 * 归并排序算法
	 * @param list     待排序的列表
	 * @param tempList 临时列表
	 * @param head     列表开始位置
	 * @param rear     列表结束位置
	 */
	public static void mergeSort(int[] list, int[] tempList, int head, int rear) {
		if (head < rear) {
			// 取分割位置
			int middle = (head + rear) / 2;
			// 递归划分列表的左序列
			mergeSort(list, tempList, head, middle);
			// 递归划分列表的右序列
			mergeSort(list, tempList, middle + 1, rear);
			// 列表的合并操作
			merge(list, tempList, head, middle + 1, rear);
		}
	}

	/**
	 * 合并操作(列表的两两合并)
	 *
	 * @param list
	 * @param tempList
	 * @param head
	 * @param middle
	 * @param rear
	 */
	public static void merge(int[] list, int[] tempList, int head, int middle, int rear) {
		// 左指针尾
		int headEnd = middle - 1;
		// 右指针头
		int rearStart = middle;
		// 临时列表的下标
		int tempIndex = head;
		// 列表合并后的长度
		int tempLength = rear - head + 1;

		// 先循环两个区间段都没有结束的情况
		while ((headEnd >= head) && (rearStart <= rear)) {
			// 如果发现右序列大，则将此数放入临时列表
			if (list[head] < list[rearStart]) {
				tempList[tempIndex++] = list[head++];
			} else {
				tempList[tempIndex++] = list[rearStart++];
			}
		}

		// 判断左序列是否结束
		while (head <= headEnd) {
			tempList[tempIndex++] = list[head++];
		}

		// 判断右序列是否结束
		while (rearStart <= rear) {
			tempList[tempIndex++] = list[rearStart++];
		}

		// 交换数据
		for (int i = 0; i < tempLength; i++) {
			list[rear] = tempList[rear];
			rear--;
		}
	}

}

