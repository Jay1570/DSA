package com.DSA.Recursion;

public class Fibonacci {
    public static void main(String[] args) {
        int n = 4;
        int ans = getFibonacci(n);
        System.out.print(ans);
    }
    static int getFibonacci(int n) {
        if (n < 2) {
            return n;
        } else {
            return getFibonacci(n - 1) + getFibonacci(n - 2);
        }
    }
}
