package com.DSA.Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubSequence {
    public static void main(String[] args) {
        subSequence("","abc");
        System.out.println(subSequence1("","abc"));
        subSequenceAscii("","abc");
        System.out.println(subSequenceAscii1("","abc"));
        System.out.println(subSet(new int[]{1, 2, 3}));
        System.out.println(subSetDuplicate(new int[]{1, 2, 2}));
    }
    static void subSequence(String p, String up) {
        if (up.isEmpty()) {
            System.out.println(p);
            return;
        }
        char ch = up.charAt(0);

        subSequence(p + ch, up.substring(1));
        subSequence(p, up.substring(1));
    }
    //adding into arraylist
    static ArrayList<String> subSequence1(String p, String up) {
        ArrayList<String> list = new ArrayList<>();
        if (up.isEmpty()) {
            list.add(p);
            return list;
        }
        char ch = up.charAt(0);

        list.addAll(subSequence1(p + ch, up.substring(1)));
        list.addAll(subSequence1(p, up.substring(1)));

        return list;
    }
    //printing ascii value
    static void subSequenceAscii(String p, String up) {
        if (up.isEmpty()) {
            System.out.println(p);
            return;
        }
        char ch = up.charAt(0);

        subSequenceAscii(p + ch, up.substring(1));
        subSequenceAscii(p, up.substring(1));
        subSequenceAscii(p + (ch+0), up.substring(1));
    }
    // returning in arraylist
    static ArrayList<String> subSequenceAscii1(String p, String up) {
        ArrayList<String> list = new ArrayList<>();
        if (up.isEmpty()) {
            list.add(p);
            return list;
        }
        char ch = up.charAt(0);

        list.addAll(subSequenceAscii1(p + ch, up.substring(1)));
        list.addAll(subSequenceAscii1(p, up.substring(1)));
        list.addAll(subSequenceAscii1(p + (ch+0), up.substring(1)));

        return list;
    }
    //using iterations(loops) for integers
    static List<List<Integer>> subSet(int[] arr) {
        List<List<Integer>> outer = new ArrayList<>();

        outer.add(new ArrayList<>());
        for (int num : arr) {
            int size = outer.size();
            for (int i = 0; i < size; i++) {
                List<Integer> internal = new ArrayList<>(outer.get(i));
                internal.add(num);
                outer.add(internal);
            }
        }

        return outer;
    }
    //for duplicate values
    static List<List<Integer>> subSetDuplicate(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> outer = new ArrayList<>();
        outer.add(new ArrayList<>());

        int start;
        int end  = 0;
        for (int j = 0; j < arr.length; j++) {
            start = 0;
            //if current and previous element is same start = end + 1
            if(j > 0 && arr[j] == arr[j-1]) {
                    start = end + 1;
            }
            end = outer.size() - 1;
            int size = outer.size();
            for (int i = start; i < size; i++) {
                List<Integer> internal = new ArrayList<>(outer.get(i));
                internal.add(arr[j]);
                outer.add(internal);
            }
        }

        return outer;
    }
}