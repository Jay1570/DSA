package com.DSA.Leetcode;

public class Problem81 {
    public boolean search(int[] nums, int target) {
        if(nums.length == 1) {
            if(nums[0] == target) {
                return true;
            } else {
                return false;
            }
        }
        int pivot = findPivot(nums);
        if (pivot == -1) {
            return binary(nums, target, 0, nums.length - 1) != -1;
        }
        if(binary(nums, target, 0, pivot) != -1) {
            return true;
        }
        return binary(nums, target, pivot + 1, nums.length - 1) != -1;
    }
    private int binary(int[] arr,int target, int start, int end){
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
    private int findPivot(int[] arr) {
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
            // if elements at middle, start and end are equal just skip
            if(arr[m] == arr[s] && arr[m] == arr[e]) {
                // check if start and end is pivot
                if(s < e && arr[s] > arr[s+1]) {
                    return s;
                }
                s++;
                if(e > s && arr[e] < arr[e-1]) {
                    return e;
                }
                e--;
            } else if(arr[s] < arr[m] || (arr[s] == arr[m] && arr[e] < arr[m])) {
                s = m + 1;
            } else {
                e = m - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Problem81 p = new Problem81();
        int[] arr = {2,2,2,3,1};
        System.out.println(p.search(arr, 1));
    }
}
