package com.DSA.Leetcode;

class Problem35 {
    public int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }

    public static void main(String[] args) {
        Problem35 p = new Problem35();
        int[] arr = {1,3,5,7};
        System.out.println(p.searchInsert(arr, 0));
    }
}
