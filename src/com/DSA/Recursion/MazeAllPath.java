package com.DSA.Recursion;

import java.util.Arrays;

public class MazeAllPath {
    public static void main(String[] args) {
        boolean[][] b = {
                {true, true, true},
                {true, true, true},
                {true, true, true}
        };
        allPath("", b, 0, 0);
        allPathPrint("", b, 0, 0, new int[b.length][b[0].length], 1);
    }
    // if all the paths available like up, down, right, left it is possible that it can re-call same path again
    // it will make recursive calls infinitely to prevent that we use backtracking
    static void allPath(String p, boolean[][] maze, int r, int c) {
        if(r == maze.length - 1 && c == maze[0].length - 1) {
            System.out.println(p);
            return;
        }
        if(!maze[r][c]) {
            return;
        }
        // making current path false so next recursive calls cannot use this path again
        maze[r][c] = false;
        if(r < maze.length - 1) {
            allPath(p + 'D', maze, r + 1, c);
        }
        if(c < maze[0].length - 1) {
            allPath(p + 'R', maze, r, c + 1);
        }
        if(r > 0) {
            allPath(p + 'U', maze, r - 1, c);
        }
        if(c > 0) {
            allPath(p + 'L', maze, r, c - 1);
        }
        // removing the changes made in recursive calls
        maze[r][c] = true;
    }
    static void allPathPrint(String p, boolean[][] maze, int r, int c, int[][] path, int step) {
        if(r == maze.length - 1 && c == maze[0].length - 1) {
            path[r][c] = step;
            for (int[] arr : path) {
                System.out.println(Arrays.toString(arr));
            }
            System.out.println(p);
            System.out.println();
            return;
         }
        if(!maze[r][c]) {
            return;
        }
        // making current path false so next recursive calls cannot use this path again
        maze[r][c] = false;
        path[r][c] = step;
        if(r < maze.length - 1) {
            allPathPrint(p + 'D', maze, r + 1, c, path, step+1);
        }
        if(c < maze[0].length - 1) {
            allPathPrint(p + 'R', maze, r, c + 1, path, step+1);
        }
        if(r > 0) {
            allPathPrint(p + 'U', maze, r - 1, c, path, step+1);
        }
        if(c > 0) {
            allPathPrint(p + 'L', maze, r, c - 1, path, step+1);
        }
        // removing the changes made in recursive calls
        maze[r][c] = true;
        path[r][c] = 0;
    }
}
