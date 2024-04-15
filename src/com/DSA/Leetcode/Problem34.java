package com.DSA.Leetcode;

import java.util.Arrays;

public class Problem34 {
    public static void main(String[] args) {
        int[] arr = {5, 7, 13};
        System.out.println(Arrays.toString(searchRange(arr, 7)));
    }
    static int[] searchRange(int[] nums, int target) {
        int[] ans = {-1, -1};
        ans[0] = search(nums, target, true);
        ans[1] = search(nums, target, false);
        return ans;
    }
    static int search(int[] nums, int target, boolean findStart){
        int ans = -1;
        int s = 0;
        int e = nums.length - 1;
        while(s <= e){
            int mid  = s + (e - s) / 2;
            if(target < nums[mid]){
                e = mid - 1;
            } else if(target > nums[mid]){
                s = mid + 1;
            } else {
                ans = mid;
                if(findStart) {
                    e = mid - 1;
                } else {
                    s = mid + 1;
                }
            }
        }
        return ans;
    }
}
