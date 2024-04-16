package com.DSA.Recursion;

import java.util.ArrayList;

public class Maze {
    public static void main(String[] args) {
        System.out.println(count(3,3));
        mazePath("", 3, 3);
        System.out.println(mazePath1("", 3, 3));
        System.out.println(mazePathDiagonal("", 3, 3));
        System.out.println(countDiagonal(3,3));
        boolean[][] b = {
                {true, false, false},
                {true, true, true},
                {true, true, true}
        };
        mazePathRestriction("", b, 0, 0);
    }
    static int count (int r, int c) {
        if(r == 1 || c == 1) {
            return 1;
        }
        return count(r - 1, c) + count(r, c - 1);
    }
    static int countDiagonal (int r, int c) {
        if(r == 1 || c == 1) {
            return 1;
        }
        return countDiagonal(r - 1, c) + countDiagonal(r, c - 1) + countDiagonal(r - 1, c - 1);
    }
    static void mazePath (String p, int r, int c) {
        if(r == 1 && c == 1) {
            System.out.println(p);
            return;
        }
        if(r > 1) {
            mazePath(p + 'D', r - 1, c);
        }
        if(c > 1) {
            mazePath(p + 'R', r, c - 1);
        }
    }
    static ArrayList<String> mazePath1 (String p, int r, int c) {
        ArrayList<String> list = new ArrayList<>();
        if(r == 1 && c == 1) {
            list.add(p);
            return list;
        }
        if(r > 1) {
            list.addAll(mazePath1(p + 'D', r - 1, c));
        }
        if(c > 1) {
            list.addAll(mazePath1(p + 'R', r, c - 1));
        }
        return list;
    }
    static ArrayList<String> mazePathDiagonal (String p, int r, int c) {
        ArrayList<String> list = new ArrayList<>();
        if(r == 1 && c == 1) {
            list.add(p);
            return list;
        }
        if(r > 1) {
            list.addAll(mazePathDiagonal(p + 'V', r - 1, c));
        }
        if(c > 1) {
            list.addAll(mazePathDiagonal(p + 'H', r, c - 1));
        }
        if(c > 1 && r > 1) {
            list.addAll(mazePathDiagonal(p + 'D', r - 1, c - 1));
        }
        return list;
    }
    static void mazePathRestriction(String p, boolean[][] maze, int r, int c) {
        if(r == maze.length - 1 && c == maze[0].length - 1) {
            System.out.println(p);
            return;
        }
        if(!maze[r][c]) {
            return;
        }
        if(r < maze.length - 1) {
            mazePathRestriction(p + 'D', maze, r + 1, c);
        }
        if(c < maze[0].length - 1) {
            mazePathRestriction(p + 'R', maze, r, c + 1);
        }
    }
}
