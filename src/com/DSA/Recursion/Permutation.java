package com.DSA.Recursion;

import java.util.ArrayList;

public class Permutation {
    public static void main(String[] args) {
        permutations("", "abc");
        System.out.println(permutations1("", "abcd"));
        System.out.println(permutationsCount("", "abc"));
    }
    // just printing
    static void permutations(String p, String up) {
        if (up.isEmpty()){
            System.out.println(p);
            return;
        }
        char ch = up.charAt(0);
        for (int i = 0; i <= p.length() ; i++) {
            String f = p.substring(0, i);
            String s = p.substring(i);
            permutations(f + ch + s, up.substring(1));
        }
    }
    // returning arraylist
    static ArrayList<String> permutations1 (String p, String up) {
        ArrayList<String> list = new ArrayList<>();
        if (up.isEmpty()){
            list.add(p);
            return list;
        }
        char ch = up.charAt(0);
        for (int i = 0; i <= p.length() ; i++) {
            String f = p.substring(0, i);
            String s = p.substring(i);
            list.addAll(permutations1(f + ch + s, up.substring(1)));
        }
        return list;
    }
    static int permutationsCount(String p, String up) {
        if (up.isEmpty()){
            return 1;
        }
        int count = 0;
        char ch = up.charAt(0);
        for (int i = 0; i <= p.length() ; i++) {
            String f = p.substring(0, i);
            String s = p.substring(i);
            count += permutationsCount(f + ch + s, up.substring(1));
        }
        return count;
    }
}
