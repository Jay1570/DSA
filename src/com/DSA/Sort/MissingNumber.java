package com.DSA.Sort;

import java.util.Arrays;

public class MissingNumber {
    public static void main(String[] args) {
        int[] a = {3,2,1,0,5,9,6,8,4};
        System.out.println(missingNumber(a));
        System.out.println(Arrays.toString(a));
    }

    static int missingNumber(int[] arr) {
        int i = 0;
        while(i < arr.length) {
            int correct = arr[i];
            if(arr[i] < arr.length && arr[i] != arr[correct]) {
                int temp = arr[i];
                arr[i] = arr[correct];
                arr[correct] = temp;
            } else {
                i++;
            }
        }
        for (i = 0; i < arr.length; i++){
            if(arr[i] != i) {
                return i;
            }
        }
        return arr.length;
    }
}