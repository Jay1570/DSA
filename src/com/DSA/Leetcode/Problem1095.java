package com.DSA.Leetcode;

public class Problem1095 {
    public static void main(String[] args) {
        int[] arr = {0, 10, 50, 60, 45, 32, 20, 12};
        System.out.println(search(arr, 9));
    }
    static int search(int[] arr, int target) {
        int peak = peakIndex1(arr);
        int f = binarySearch(arr, target, 0, peak);
        if(f != -1) {
            return f;
        }
        return binarySearch(arr, target, peak, arr.length - 1);
    }
    static int binarySearch(int[] arr, int target, int start, int end) {
        boolean isAsc = arr[start] < arr[end];

        while(start <= end){
            int mid  = start + (end - start) / 2;
            if(arr[mid] == target){
                return mid;
            }
            if(isAsc) {
                if (target < arr[mid]) {
                    end = mid - 1;
                } else if (target > arr[mid]) {
                    start = mid + 1;
                }
            } else {
                if (target > arr[mid]) {
                    end = mid - 1;
                } else if (target < arr[mid]) {
                    start = mid + 1;
                }
            }
        }
        return -1;
    }
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
