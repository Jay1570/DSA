package com.DSA.Leetcode;

public class Problem410 {
    public static void main(String[] args) {

    }
    static int splitArray(int[] nums, int m) {
        int s = 0;
        int e = 0;

        for (int i = 0; i < nums.length; i++) {
            s = Math.max(s, nums[i]);
            e += nums[i];
        }
        while(s < e) {
            // try for middle as potential ans
            int mid = s + (e - s) / 2;

            // calculate how many pieces you can divide this in with this max sum
            int sum = 0;
            int pieces = 1;
            for(int num : nums) {
                if(sum + num > mid) {
                    sum = num;
                    pieces++;
                } else {
                    sum += num;
                }
            }
            if(pieces > m) {
                s = mid + 1;
            } else {
                e = mid;
            }
        }
        return e;
    }
}
