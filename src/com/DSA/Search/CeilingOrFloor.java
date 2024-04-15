package com.DSA.Search;

public class CeilingOrFloor {
    public static void main(String[] args) {
        int[] arr = {12, 13, 16, 43, 53, 59, 100};
        int target = 15;
        System.out.println(ceiling(arr, target));
        System.out.println(floor(arr, target));
    }
    static int ceiling(int[] arr, int target) {
        if(target > arr[arr.length - 1]) {
            return -1;
        }
        int start = 0;
        int end = arr.length - 1;
        while(start <= end){
            int mid  = start + (end - start) / 2;
            if(target < arr[mid]){
                end = mid - 1;
            } else if(target > arr[mid]){
                start = mid + 1;
            } else {
                return arr[mid];
            }
        }
        return arr[start];
    }
    static int floor(int[] arr, int target) {
        if(target < arr[arr.length - 1]) {
            return -1;
        }
        int start = 0;
        int end = arr.length - 1;
        while(start <= end){
            int mid  = start + (end - start) / 2;
            if(target < arr[mid]){
                end = mid - 1;
            } else if(target > arr[mid]){
                start = mid + 1;
            } else {
                return arr[mid];
            }
        }
        return arr[end];
    }
}
