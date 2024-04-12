package com.DSA.Recursion;

public class CheckSorted {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,4};
        System.out.println(isSorted(arr, 0));
    }
    static boolean isSorted(int[] arr , int pointer) {
        if(pointer == arr.length - 1) {
            return true;
        }
        return arr[pointer] <= arr[pointer + 1] && isSorted(arr, pointer + 1);
//        if(arr[pointer] <= arr[pointer + 1]) {
//            return isSorted(arr, pointer + 1);
//        } else {
//            return false;
//        }
    }
}
