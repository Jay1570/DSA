package com.DSA.Search;

public class SquareRoot {
    public static void main(String[] args) {
        System.out.println(mySqrt(1));
    }
    static public int mySqrt(int x){
        if(x < 0) return -1;
        if(x == 1) return 1;
        int start = 1;
        long mid;
        int end =  x / 2;
        while(start <= end) {
            mid = start + (end - start) / 2;
            if((mid * mid) == x) {
                return (int)mid;
            }
            if((mid * mid) < x) {
                start = (int)mid + 1;
            } else {
                end = (int)mid - 1;
            }
        }
        return end;
    }
}
