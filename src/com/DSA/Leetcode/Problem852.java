package com.DSA.Leetcode;

public class Problem852 {
    public static void main(String[] args) {
        int[] arr = {2, 1};
        //System.out.println(peakIndex(arr));
        System.out.println(peakIndex1(arr));
    }
    //my implementation
    static int peakIndex(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        while(start < end){
            int mid  = start + (end - start) / 2;
            if(arr[mid - 1] < arr[mid] && arr[mid] > arr[mid + 1]) {
                return mid;
            } else {
                if(arr[mid - 1] < arr[mid]) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }
        }
        return start;
    }
    //better way
    static int peakIndex1(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        while(start < end){
            int mid  = start + (end - start) / 2;
            if(arr[mid] > arr[mid + 1]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
}
