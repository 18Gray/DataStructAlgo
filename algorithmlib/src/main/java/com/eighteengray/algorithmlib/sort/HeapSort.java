package com.eighteengray.algorithmlib.sort;


/**
 *堆排序(Heap Sort) 利用堆（一般为大根堆）进行排序的方法。它的基本思想是：将待排序的元素构造成一个大根堆。此时，整个序列的最大值就是堆顶的根节点。将它移走（其实就是将它与数组的末尾元素进行交换，此时末尾元素就是最大值），然后将剩余的length-1 个元素重新构造成一个大根堆，这样就会得到length个元素中的次大值。如此反复执行，便能得到一个有序的序列。
 堆是具有下列性质的完全二叉树：每个节点的值都大于或等于其左右孩子节点的值，称为大根堆；每个节点的值都小于或等于其左右孩子节点的值，称为小根堆。
 堆排序的最坏时间复杂度为O(n*log2n)，平均时间复杂度为O(n*log2n)
 */

class HeapSort {
	public static void main(String[] args) {
		int[] list = {1, 3, 4, 5, 2, 6, 9, 7, 8, 0};
		System.out.println("排序后：");
		heapSort(list);
	}

	/**
	 * 堆排序算法
	 */
	public static void heapSort(int[] list) {
		// 将无序堆构造成一个大根堆，大根堆有length/2个父节点
		for (int i = list.length / 2 - 1; i >= 0; i--) {
			headAdjust(list, i, list.length);
		}

		// 逐步将每个最大值的根节点与末尾元素交换，并且再调整其为大根堆
		for (int i = list.length - 1; i > 0; i--) {
			// 将堆顶节点和当前未经排序的子序列的最后一个元素交换位置
			swap(list, 0, i);
			headAdjust(list, 0, i);
		}
	}

	/**
	 * 构造大根堆
	 */
	public static void headAdjust(int[] list, int parent, int length) {
		// 保存当前父节点
		int temp = list[parent];

		// 得到左孩子节点
		int leftChild = 2 * parent + 1;

		while (leftChild < length) {
			// 如果parent有右孩子，则要判断左孩子是否小于右孩子
			if (leftChild + 1 < length && list[leftChild] < list[leftChild + 1]) {
				leftChild++;
			}
			// 父亲节点大于子节点，就不用做交换
			if (temp >= list[leftChild]) {
				break;
			}
			// 将较大子节点的值赋给父亲节点
			list[parent] = list[leftChild];
			// 然后将子节点做为父亲节点
			parent = leftChild;
			// 找到该父亲节点较小的左孩子节点
			leftChild = 2 * parent + 1;
		}
		// 最后将temp值赋给较大的子节点，以形成两值交换
		list[parent] = temp;
	}

	/**
	 * 交换数组中两个位置的元素
	 */
	public static void swap(int[] list, int top, int last) {
		int temp = list[top];
		list[top] = list[last];
		list[last] = temp;
	}


}
