package com.DSA.Recursion;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {12,21,54,211,12,34};
        arr = mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    //Recursively sorts an array using merge sort
    static int[] mergeSort(int[] arr) {
        if(arr.length == 1) {
            return arr;
        }
        int mid = arr.length / 2;
        int[] l = mergeSort(Arrays.copyOfRange(arr, 0, mid));
        int[] r = mergeSort(Arrays.copyOfRange(arr, mid, arr.length));

        return merge(l, r);
    }
    //merges two sorted arrays into one
    static int[] merge(int[] f, int[] s) {
        int[] mix = new int[f.length + s.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < f.length && j < s.length) {
            if (f[i] < s[j]) {
                mix[k] = f[i];
                i++;
            } else {
                mix[k] = s[j];
                j++;
            }
            k++;
        }
        // adds remaining elements from one of the arrays
        while (i < f.length) {
            mix[k] = f[i];
            i++;
            k++;
        }
        while (j < s.length) {
            mix[k] = s[j];
            j++;
            k++;
        }
        return mix;
    }
}
