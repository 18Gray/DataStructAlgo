package com.eighteengray.algorithmlib.sort;

/**
 * ��������
 * @author Razer
 */
public class InsertSort
{

	/**
	 * ��������
	 * �ӵ�һ��Ԫ�ؿ�ʼ����Ԫ�ؿ�����Ϊ�Ѿ������� ȡ����һ��Ԫ�أ����Ѿ������Ԫ�������дӺ���ǰɨ��
	 * �����Ԫ�أ������򣩴�����Ԫ�أ�����Ԫ���Ƶ���һλ�� �ظ�����3��ֱ���ҵ��������Ԫ��С�ڻ��ߵ�����Ԫ�ص�λ�� ����Ԫ�ز��뵽��λ���� �ظ�����2
	 * @param numbers ����������
	 */
	public static void insertSort(int[] numbers)
	{
		int size = numbers.length;
		int temp = 0;
		int j = 0;

		for (int i = 0; i < size; i++)
		{
			temp = numbers[i];
			// ����temp��ǰ���ֵС����ǰ���ֵ����
			for (j = i; j > 0; j--)
			{
				if(temp < numbers[j - 1])
				{
					numbers[j] = numbers[j - 1];
				}
			}
			numbers[j] = temp;
		}
	}

}
