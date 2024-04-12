package com.DSA.Sort;

import java.util.Arrays;


public class CycleSort {
    public static void main(String[] args) {
        int[] a = {3,5,2,1,4};
        cycleSort(a);
        System.out.println(Arrays.toString(a));
    }
    static void cycleSort(int[] arr) {
        int i = 0;
        while(i < arr.length) {
            int correct = arr[i] - 1;
            if(arr[i] != arr[correct]) {
                int temp = arr[i];
                arr[i] = arr[correct];
                arr[correct] = temp;
            } else {
                i++;
            }
        }
    }
}
