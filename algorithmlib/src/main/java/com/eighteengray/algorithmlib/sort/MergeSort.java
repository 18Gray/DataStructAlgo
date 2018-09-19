package com.eighteengray.algorithmlib.sort;


import java.util.Arrays;

/**
 * 归并排序是建立在归并操作上的一种有效的排序算法，该算法是采用分治法的典型应用。
 它指的是将两个已经排序的序列合并成一个序列的操作。归并排序算法依赖归并操作。归并排序有多路归并排序、两路归并排序 , 可用于内排序，也可以用于外排序。这里仅对内排序的两路归并方法进行讨论。

 算法思路：
 把 n 个记录看成 n 个长度为 l 的有序子表
 进行两两归并使记录关键字有序，得到 n/2 个长度为 2 的有序子表
 重复第 2 步直到所有记录归并成一个长度为 n 的有序表为止。
 算法复杂度分析：
 在最后一步，需要依次遍历两个已排序好的数组，此时的时间复杂度为O(n)。
 同时又进行着二路归并，形成一颗完全二叉树，此时整个排序需要进行log2n次。
 所以归并排序的时间复杂度为O(nlogn)。这是它的最好、最坏、平均的时间性能。
 */

class MergeSort {
	public static void main(String []args){
		int []arr = {9,8,7,6,5,4,3,2,1};
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public static void sort(int []arr){
		int []temp = new int[arr.length];//在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
		sort(arr,0,arr.length-1,temp);
	}

	private static void sort(int[] arr,int left,int right,int []temp){
		if(left<right){
			int mid = (left+right)/2;
			sort(arr,left,mid,temp);//左边归并排序，使得左子序列有序
			sort(arr,mid+1,right,temp);//右边归并排序，使得右子序列有序
			merge(arr,left,mid,right,temp);//将两个有序子数组合并操作
		}
	}

	private static void merge(int[] arr,int left,int mid,int right,int[] temp){
		int i = left;//左序列指针
		int j = mid+1;//右序列指针
		int t = 0;//临时数组指针，指向最终的temp
		while (i<=mid && j<=right){
			if(arr[i]<=arr[j]){
				temp[t++] = arr[i++];
			}else {
				temp[t++] = arr[j++];
			}
		}
		while(i<=mid){//将左边剩余元素填充进temp中
			temp[t++] = arr[i++];
		}
		while(j<=right){//将右序列剩余元素填充进temp中
			temp[t++] = arr[j++];
		}
		t = 0;
		//将temp中的元素全部拷贝到原数组中
		while(left <= right){
			arr[left++] = temp[t++];
		}
	}
}

