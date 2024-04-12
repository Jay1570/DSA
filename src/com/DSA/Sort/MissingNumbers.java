package com.DSA.Sort;

import java.util.ArrayList;
import java.util.List;

public class MissingNumbers {
    public static void main(String[] args) {
        int[] a = {3,1,5,9,6,8,4};
        System.out.println(missingNumber(a));
    }
    static List<Integer> missingNumber(int[] arr) {
        int i = 0;
        List<Integer> m = new ArrayList<>();
        while(i < arr.length) {
            int correct = arr[i] - 1;
            if(arr[i] < arr.length && arr[i] != arr[correct]) {
                int temp = arr[i];
                arr[i] = arr[correct];
                arr[correct] = temp;
            } else {
                i++;
            }
        }
        for (i = 0; i < arr.length; i++){
            if(arr[i] != i + 1) {
                m.add(i + 1);
            }
        }
        return m;
    }
}
