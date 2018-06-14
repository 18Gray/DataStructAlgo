package com.eighteengray.algorithmlib.sort;

import com.razergene.datastructalgo.common.Utils;



/**
 * ð������
 * ����һ��ʱ����������Ԫ�رȽϣ�С�߷�ǰ�����߷ź󡣾���һ������Ԫ�ػᵽ�����Ϊֻ��
 * ��һ��Ԫ���ܸ�����Ԫ�ضԱȴ�С��
 * �����پ���N-1�α�����ÿ�ζ�����ǰN-i��Ԫ�أ��ҵ����Ԫ�ط����
 * ʣ�����һ��Ԫ����Ȼ����С�ģ������������
 * ********
 * ******
 * ****
 * ***
 * **
 * *
 * @author Razer
 */
public class BubbleSort
{
	
	public static void bubbleSort(int[] numbers)
    {
        int temp = 0;
        int size = numbers.length;
        for(int i = 0 ; i < size-1; i++)  //������������N-1�Ĵ���
        {
        	for(int j = 0;j < size-1-i; j++) //����������ȥ����������Ԫ��
        	{
        		if(numbers[j] > numbers[j+1])  //��������λ��
        		{
        			Utils.switchNum(numbers[j], numbers[j+1]);
        		}
        	}
        }
    }

	

	
}