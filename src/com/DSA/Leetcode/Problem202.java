package com.DSA.Leetcode;

public class Problem202 {
    public boolean isHappy(int n) {
        int s = n;
        int f = n;
        do {
            s = findSquare(s);
            f = findSquare(findSquare(f));
        } while (s != f);
        if(s == 1) {
            return true;
        }
        return false;
    }
    private int findSquare(int n) {
        int ans = 0;
        while (n > 0) {
            int rem = n % 10;
            ans += rem*rem;
            n  = n / 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        Problem202 p = new Problem202();
        System.out.println(p.isHappy(2));
    }
}
