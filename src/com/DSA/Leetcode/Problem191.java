package com.DSA.Leetcode;

public class Problem191 {
    public static void main(String[] args) {
        System.out.println(hammingWeight(12));
    }
    static int hammingWeight(int n) {
        int count = 0;
        while (n>0) {
            if((n & 1) == 1) {
                count++;
            }
            n = n>>1;
        }
        return count;
    }
}
