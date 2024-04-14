package com.DSA.Recursion;

public class RemoveGivenCharacter {
    public static void main(String[] args) {
        String s = "baccad";
        System.out.println(remove(s,0,'c'));
        skip("", s);
        System.out.println(skip1(s));
        System.out.println(skipApple("baappleccd"));
        System.out.println(skipApple1("baapplccd"));
    }
    //my implementation
    static StringBuilder remove(String s, int start, char target) {
        StringBuilder ans = new StringBuilder();
        if (start == s.length()) {
            return ans;
        }
        if (s.charAt(start) != target) {
            ans.append(s.charAt(start));
        }
        return ans.append(remove(s, start + 1, target));
    }
    //better versions
    //passing ans string as argument
    static void skip(String p, String up) {
        if(up.isEmpty()) {
            System.out.println(p);
            return;
        }
        char ch = up.charAt(0);
        if(ch == 'a') {
            skip(p, up.substring(1));
        } else {
            skip(p + ch, up.substring(1));
        }
    }
    //returning ans string
    static String skip1(String up) {
        if(up.isEmpty()) {
            return "";
        }
        char ch = up.charAt(0);
        if(ch == 'a') {
            return skip1(up.substring(1));
        } else {
            return ch + skip1(up.substring(1));
        }
    }
    // skipping whole substring
    static String skipApple(String up) {
        if(up.isEmpty()) {
            return "";
        }
        if(up.startsWith("apple")) {
            return skipApple(up.substring(5));
        } else {
            return up.charAt(0) + skipApple(up.substring(1));
        }
    }
    // skipping part of the given substring when whole string is not available
    static String skipApple1(String up) {
        if(up.isEmpty()) {
            return "";
        }
        if(up.startsWith("app") && !up.startsWith("apple")) {
            return skipApple1(up.substring(3));
        } else {
            return up.charAt(0) + skipApple1(up.substring(1));
        }
    }
}
