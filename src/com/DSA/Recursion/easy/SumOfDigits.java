package com.DSA.Recursion.easy;

public class SumOfDigits {
    public static void main(String[] args) {
        System.out.println(sum(12345));
        System.out.println(product(123045));
        reverse(123045);
    }
    static int sum(int n) {
        if(n == 0) {
            return 0;
        }
        return (n%10) + sum(n/10);
    }
    static int product(int n) {
        if(n%10 == n) {
            return n;
        }
        return (n%10) * product(n/10);
    }
    static void reverse(int n) {
        if(n%10 == n) {
            System.out.print(n);
            return;
        }
        System.out.print(n%10);
        reverse(n/10);
    }
    static void reverse2(int n) {
        int digits = (int)(Math.log10(n)) + 1;
        if(n%10 == n) {
            System.out.print(n);
            return;
        }
        System.out.print(n%10);
        reverse2(n/10);
    }
}
