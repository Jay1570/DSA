package com.DSA.Recursion;

public class Recursion {
    public static void main(String[] args) {
        recursion(1,5);
    }
    static void recursion(int start, int end) {

        if(end < start) {
            return;
        }
        System.out.println(end);
        //this is called tail recursion
        recursion(start,end-1);
    }
}
