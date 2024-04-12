package com.DSA.Math;

public class NonRepeating {
    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 1, 2, 1, 3, 6, 4};
        int unique = 0;
        for(int n: arr) {
            unique ^= n;
        }
        System.out.println(unique);
    }
}
