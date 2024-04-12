package com.DSA.Search;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {-12,-10,-4,0,21,32,43,56,78,99,165,543,590};
        int target = -4;
        System.out.println("Index :- " + binarySearch(arr,target));
    }
    // returns index
    static int binarySearch(int[] arr,int target){
        int start = 0;
        int end = arr.length - 1;
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
}
