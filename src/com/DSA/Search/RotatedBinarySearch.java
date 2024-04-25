package com.DSA.Search;

public class RotatedBinarySearch {
    public static void main(String[] args) {
        int[] arr = {3, 1, 1, 1, 1, 1};
        System.out.println(search(arr, 3));
        System.out.println(rotationCount(arr));
    }
    public static int search(int[] arr, int target) {
        int pivot = findPivotWithDuplicate(arr);
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
    // this will not work with dulicate values
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
    static int findPivotWithDuplicate(int[] arr) {
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
                if(arr[s] > arr[s+1]) {
                    return s;
                }
                s++;
                if(arr[e] < arr[e-1]) {
                    return e;
                }
                e--;
            } else if(arr[s] > arr[m] || (arr[s] == arr[m] && arr[e] < arr[m])) {
                s = m + 1;
            } else {
                e = m - 1;
            }
        }
        return -1;
    }
    static int rotationCount(int[] arr) {
        return findPivotWithDuplicate(arr) + 1;
    }
}
