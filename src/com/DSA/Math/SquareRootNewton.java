package com.DSA.Math;

public class SquareRootNewton {
    public static void main(String[] args) {
        System.out.println(sqrt(40));
    }
    static double sqrt(int n) {
        double x = n, root;
        while (true) {
            root = 0.5 *  (x + (n / x));
            if (Math.abs(root - x) < 0.5) {
                break;
            }
            x = root;
        }
        return root;
    }
}
