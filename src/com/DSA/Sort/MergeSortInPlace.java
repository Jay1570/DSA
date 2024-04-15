package com.DSA.Sort;

import java.util.Arrays;

public class MergeSortInPlace {
    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
        mergeSort(arr, 0, arr.length);
        System.out.println(Arrays.toString(arr));
    }
    //Recursively sorts an array using merge sort
    static void mergeSort(int[] arr, int s, int e) {
        if(e - s == 1) {
            return;
        }
        int m = (s + e) / 2;
        mergeSort(arr, s, m);
        mergeSort(arr, m, e);

        merge(arr, s, m, e);
    }
    //merges two sorted arrays into one
    static void merge(int[] arr, int s, int m, int e) {
        int[] mix = new int[e - s];
        int i = s;
        int j = m;
        int k = 0;
        while (i < m && j < e) {
            if (arr[i] < arr[j]) {
                mix[k] = arr[i];
                i++;
            } else {
                mix[k] = arr[j];
                j++;
            }
            k++;
        }
        // adds remaining elements from one of the arrays
        while (i < m) {
            mix[k] = arr[i];
            i++;
            k++;
        }
        while (j < e) {
            mix[k] = arr[j];
            j++;
            k++;
        }
        System.arraycopy(mix, 0, arr, s, mix.length);
    }
}
