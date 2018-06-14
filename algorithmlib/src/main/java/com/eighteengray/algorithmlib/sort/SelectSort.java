package com.eighteengray.algorithmlib.sort;

import com.razergene.datastructalgo.common.Utils;

/**
 * ѡ������
 * 
 * @author Razer
 */
public class SelectSort
{

	/**
	 * ѡ�������㷨 ��δ�����������ҵ���СԪ�أ���ŵ��������е���ʼλ�� �ٴ�ʣ��δ����Ԫ���м���Ѱ����СԪ�أ�Ȼ��ŵ���������ĩβ��
	 * �Դ����ƣ�ֱ������Ԫ�ؾ�������ϡ�
	 * @param numbers
	 */
	public static void selectSort(int[] numbers)
	{
		for(int i=0;i<numbers.length;i++)
		{
			int min = Integer.MAX_VALUE;
			int index = 0;
			//�����ҵ�i֮�󣨰���i������Сֵ
			for(int j=i;j<numbers.length;j++)
			{
				if(numbers[j] < min)
				{
					min = numbers[j];
					index = j;
				}
			}
			Utils.switchNum(numbers[i], numbers[index]);
		}
		
	}


}