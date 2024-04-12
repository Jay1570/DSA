package com.DSA.Recursion;

import java.util.ArrayList;

public class LinearSearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 4, 5};
        System.out.println(searchIndex(arr, 5, 0));
        System.out.println(searchIndexAll2(arr, 4, 0));
    }
    static boolean search(int[] arr, int target, int index) {
        if(index == arr.length) {
            return false;
        }
        return arr[index] == target || search(arr, target, index + 1);
    }
    static int searchIndex(int[] arr, int target, int index) {
        if(index == arr.length) {
            return -1;
        }
        if(arr[index] == target) {
            return index;
        } else {
            return searchIndex(arr, target, index + 1);
        }
    }
    static int searchIndexLast(int[] arr, int target, int index) {
        if(index == -1) {
            return -1;
        }
        if(arr[index] == target) {
            return index;
        } else {
            return searchIndexLast(arr, target, index - 1);
        }
    }
    static ArrayList<Integer> searchIndexAll(int[] arr, int target, int index, ArrayList<Integer> list) {
        if(index == arr.length) {
            return list;
        }
        if(arr[index] == target) {
            list.add(index);
        }
        return searchIndexAll(arr, target, index + 1, list);
    }
    static ArrayList<Integer> searchIndexAll2(int[] arr, int target, int index) {
        ArrayList<Integer> list = new ArrayList<>();
        if(index == arr.length) {
            return list;
        }
        if(arr[index] == target) {
            list.add(index);
        }
        list.addAll(searchIndexAll2(arr, target, index + 1));
        return list;
    }
}
