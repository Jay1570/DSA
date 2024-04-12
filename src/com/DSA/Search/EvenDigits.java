package com.DSA.Search;

public class EvenDigits {
    public static void main(String[] args) {
        int[] num = {12,234,3223,12,456,87237,312122};
        System.out.println(findNumbers(num));
    }
    static int findNumbers(int[] arr){
        int count =0;
        for(int v : arr){
            if(even(v)){
                count++;
            }
        }
        return count;
    }
    static boolean even(int value){
        return digits(value) % 2 == 0;
    }
    static int digits(int value){
        if(value < 0){
            value *= -1;
        }
        return (int)(Math.log10(value)) + 1;
    }
}
