package com.DSA.Sort;

import java.util.Arrays;

public class SelectionSort {
    public static void main(String[] args) {
        int[] a = {1,2,3,4,8,6,10};
        selectionSort(a);
        System.out.println(Arrays.toString(a));
    }
    static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int last = arr.length - i - 1;
            int maxIndex = maxIndex(arr, last);
            swap(arr, maxIndex, last);
        }
    }
    static void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }
    static int maxIndex(int[] arr, int end) {
        int max = 0;
        for (int i = 0; i <= end; i++) {
            if(arr[max] < arr[i]) {
                max = i;
            }
        }
        return max;
    }
}
