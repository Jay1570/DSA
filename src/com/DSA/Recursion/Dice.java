package com.DSA.Recursion;

import java.util.ArrayList;

public class Dice {
    public static void main(String[] args) {
        dice("", 4);
        System.out.println(dice1("", 4));
        System.out.println(diceFace("", 0, 3));
    }
    static void dice(String p, int target) {
        if(target == 0) {
            System.out.println(p);
            return;
        }
        for (int i = 1; i <= 6 && i <= target; i++) {
            dice(p + i, target - i);
        }
    }
    static ArrayList<String> dice1(String p, int target) {
        ArrayList<String> list = new ArrayList<>();
        if(target == 0) {
            list.add(p);
            return list;
        }
        for (int i = 1; i <= 6 && i <= target; i++) {
            list.addAll(dice1(p + i, target - i));
        }
        return list;
    }
    // custom face dice
    static ArrayList<String> diceFace(String p, int target, int face) {
        ArrayList<String> list = new ArrayList<>();
        if(target == 0) {
            list.add(p);
            return list;
        }
        for (int i = 1; i <= face && i <= target; i++) {
            list.addAll(diceFace(p + i, target - i, face));
        }
        return list;
    }
}
