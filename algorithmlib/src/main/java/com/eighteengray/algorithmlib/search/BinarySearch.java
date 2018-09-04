package com.eighteengray.algorithmlib.search;


/**
 * 二分查找的基本思想是：在有序表中，取中间元素作为比较对象，若给定值与中间元素相等，则查找成功；若给定值小于中间元素，则在中间元素的左半区继续查找；若给定值大于中间元素，则在中间元素的右半区继续查找。不断重复上述过程，直到找到为止。
 从二分查找的定义我们可以看出，使用二分查找有两个前提条件：
 （1）待查找的列表必须有序。
 （2）必须使用线性表的顺序存储结构来存储数据。
 二分查找的时间复杂度为O(log2n)
 */
public class BinarySearch
{
	public static void main(String[] args) {
		int[] list = {10, 20, 30, 40, 50, 60, 70, 80, 90};

		int result = binarySearch(list, 30);
		if (result != -1) {
			System.out.println("30在列表中的位置是：" + result);
		} else {
			System.out.println("对不起，列表中不存在该元素！");
		}
	}

	/**
	 * 二分查找
	 */
	public static int binarySearch(int[] list, int key) {
		int low = 0;
		int high = list.length - 1;

		while (low <= high) {
			int middle = (low + high) / 2;

			// 判断中间元素是否与给定值相等
			if (list[middle] == key) {
				return middle;
			} else {
				if (list[middle] > key) {
					// 在中间元素的左半区查找
					high = middle - 1;
				} else {
					// 在中间元素的右半区查找
					low = middle + 1;
				}
			}
		}
		// 没有找到（查找失败）
		return -1;
	}

}

