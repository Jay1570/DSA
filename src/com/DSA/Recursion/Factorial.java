package com.DSA.Recursion;

public class Factorial {
    public static void main(String[] args) {
        System.out.println(getFactorial(5));
    }
    static int getFactorial(int n) {
        if(n == 0) {
            return 1;
        }
        return n * getFactorial(n - 1);
    }
}
