package com.DSA.Search;

public class Main {
    public static void main(String[] args) {
        int[] num = {19,39,32,12,31,12,4,121};
        System.out.println(linearSearch(num,1212));
    }
    static int linearSearch(int[] arr,int target){
        if(arr.length == 0){
            return -1;
        }
        for (int i=0;i<arr.length;i++){
            if(target == arr[i]){
                return i;
            }
        }
        return -1;
    }
}
