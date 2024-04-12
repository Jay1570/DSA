package com.DSA.Search;

public class OrderAgnosticBS {
    public static void main(String[] args) {
        int[] arr = {-12,-10,-4,0,21,32,43,56,78,99,165,543,590};
        int target = 590;
        System.out.println("Index :- " + orderAgnosticBS(arr,target));
    }
    static int orderAgnosticBS(int[] arr,int target){
        int start = 0;
        int end = arr.length - 1;
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
}
