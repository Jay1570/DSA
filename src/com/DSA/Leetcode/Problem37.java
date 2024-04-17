package com.DSA.Leetcode;

public class Problem37 {
    public static void main(String[] args) {
        char[][] board = {
                {'3', '.', '6', '5', '.', '8', '4', '.', '.'},
                {'5', '2', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '8', '7', '.', '.', '.', '.', '3', '1'},
                {'.', '.', '3', '.', '1', '.', '.', '8', '.'},
                {'9', '.', '.', '8', '6', '3', '.', '.', '5'},
                {'.', '5', '.', '.', '9', '.', '6', '.', '.'},
                {'1', '3', '.', '.', '.', '.', '.', '5', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '7', '4'},
                {'.', '.', '5', '2', '.', '6', '3', '.', '.'}
        };
        if(solve(board)) {
            display(board);
        } else {
            System.out.println("Cannot Solve");
        }
    }
    static boolean solve(char[][] board) {
        int n = board.length;
        int row = -1;
        int col = -1;
        boolean emptyLeft = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.') {
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
        for (char number = '1'; number <= '9'; number++) {
            if(isSafe(board, row, col, number)) {
                board[row][col] = number;
                if(solve(board)) {
                    return true;
                } else {
                    board[row][col] = '.';
                }
            }
        }
        return false;
    }

    private static void display(char[][] board) {
        for (char[] n : board) {
            for (char num: n) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    static boolean isSafe(char[][] board, int row, int col, char num) {
        // check the row
        for(int i = 0; i < board.length; i++) {
            // check if number is in the row
            if(board[row][i] == num) {
                return false;
            }
        }
        // check the column
        for(char[] nums : board) {
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
