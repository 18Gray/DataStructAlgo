package com.eighteengray.algorithmlib.sort;

public class CommonUtil
{
    public static void swap(int[] array, int left, int right){
        int temp;
        if (array != null && array.length > 0) {
            temp = array[left];
            array[left] = array[right];
            array[right] = temp;
        }
    }
}
