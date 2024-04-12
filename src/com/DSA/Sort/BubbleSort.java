package com.DSA.Sort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] a = {1,2,3,4,8,6,10};
        bubbleSort(a);
        System.out.println(Arrays.toString(a));
    }
    static void bubbleSort(int[] arr) {
        boolean swapped;
        int temp;
        for(int i = 0;i < arr.length - 1;i++) {
            swapped = false;
            for(int j =1;j < arr.length - i;j++) {
                if (arr[j] < arr[j-1]) {
                    temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                    swapped = true;
                }
            }
            if(!swapped) {
                break;
            }
        }
    }
}
