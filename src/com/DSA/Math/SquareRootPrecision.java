package com.DSA.Math;

public class SquareRootPrecision {
    public static void main(String[] args) {
        int n = 40;
        int p = 3;
        System.out.printf("%.3f", mySqrt(n, p));
    }
    public static double mySqrt(int x, int p) {
        if(x < 0) return -1;
        if(x == 1) return 1;
        int s = 0;
        int m;
        int e =  x / 2;
        double root = 0.0, incr = 0.1;
        while(s <= e) {
            m = s + (e - s) / 2;
            if((m * m) == x) {
                return m;
            }
            if((m * m) < x) {
                s = m + 1;
            } else {
                e = m - 1;
            }
        }
        for (int i = 0; i < p; i++) {
            while (root * root <= x) {
                root += incr;
            }
            root -= incr;
            incr /= 10;
        }
        return root;
    }
}
