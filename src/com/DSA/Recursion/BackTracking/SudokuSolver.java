package com.DSA.Recursion.BackTracking;

public class SudokuSolver {
    public static void main(String[] args) {
        int[][] board = new int[][]
        {
                {3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}
        };
        if(solve(board)) {
            display(board);
        } else {
            System.out.println("Cannot Solve");
        }
    }
    static boolean solve(int[][] board) {
        int n = board.length;
        int row = -1;
        int col = -1;
        boolean emptyLeft = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) {
                    row = i;
                    col = j;
                    emptyLeft = false;
                    break;
                }
            }
            // if you found some empty element in roe then break
            if(!emptyLeft) {
                break;
            }
        }
        if(emptyLeft) {
            return true;
        }
        // backtrack
        for (int number = 1; number <= n; number++) {
            if(isSafe(board, row, col, number)) {
                board[row][col] = number;
                if(solve(board)) {
                    return true;
                } else {
                 board[row][col] = 0;
                }
            }
        }
        return false;
    }

    private static void display(int[][] board) {
        for (int[] n : board) {
            for (int num: n) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    static boolean isSafe(int[][] board, int row, int col, int num) {
        // check the row
        for(int i = 0; i < board.length; i++) {
            // check if number is in the row
            if(board[row][i] == num) {
                return false;
            }
        }
        // check the column
        for(int[] nums : board) {
            // check if number is in the row
            if(nums[col] == num) {
                return false;
            }
        }
        int sqrt = (int)(Math.sqrt(board.length));
        int s = row - (row % sqrt);
        int e = col - (col % sqrt);

        for (int r = s; r < s + sqrt; r++) {
            for (int c = e; c < e + sqrt; c++) {
                if(board[r][c] == num) {
                    return false;
                }
            }
        }
        return true;
    }

}
