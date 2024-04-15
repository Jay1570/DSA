package com.DSA.Search;

public class RotatedBinarySearch {
    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(search(arr, 0));
    }
    public static int search(int[] arr, int target) {
        int pivot = findPivot(arr);
        if(pivot == -1) {
            return binarySearch(arr, target, 0, arr.length - 1);
        }
        int f = binarySearch(arr, target, 0, pivot);
        if(f != -1) {
            return f;
        }
        return binarySearch(arr, target, pivot + 1, arr.length -  1);
    }
    static int binarySearch(int[] arr,int target, int start, int end){
        while(start <= end){
            int mid  = start + (end - start) / 2;
            if(target < arr[mid]){
                end = mid - 1;
            } else if(target > arr[mid]){
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
    static int findPivot(int[] arr) {
        int s = 0;
        int e = arr.length - 1;
        while (s <= e) {
            int m = s + (e - s) / 2;
            if(m < e && arr[m] > arr[m+1]) {
                return m;
            }
            if(m > s && arr[m] < arr[m-1]) {
                return m - 1;
            }
            if(arr[m] <= arr[s]) {
                e = m - 1;
            } else {
                s = m + 1;
            }
        }
        return -1;
    }
}
