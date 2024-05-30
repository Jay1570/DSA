package com.DSA.Leetcode;

public class Problem74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int rStart = 0;
        int rEnd = rows - 1;
        int cMid = cols / 2;

        if (rows == 1) {
            return binarySearch(matrix, 0, 0, cols - 1, target);
        }
        while (rStart < (rEnd - 1)) {
            int rMid = rStart + (rEnd - rStart) / 2;
            if (matrix[rMid][cMid] == target) {
                return true;
            }
            if (matrix[rMid][cMid] < target) {
                rStart = rMid;
            } else {
                rEnd = rMid;
            }
        }
        if (matrix[rStart][cMid] == target) {
            return true;
        }
        if (matrix[rStart+1][cMid] == target) {
            return true;
        }
        if (cMid > 0 && matrix[rStart][cMid - 1] >= target) {
            return binarySearch(matrix, rStart, 0, cMid - 1, target);
        }
        if (cMid < cols -1 && matrix[rStart][cMid + 1] <= target && matrix[rStart][cols-1] >= target) {
            return binarySearch(matrix, rStart, cMid + 1, cols - 1, target);
        }
        if (cMid > 0 && matrix[rStart + 1][cMid - 1] >= target) {
            return binarySearch(matrix, rStart + 1, 0, cMid - 1, target);
        } else {
            return binarySearch(matrix, rStart + 1, cMid + 1, cols - 1, target);
        }
    }
    private boolean binarySearch(int[][] matrix, int row, int cStart, int cEnd,int target) {
        while (cStart <= cEnd) {
            int cMid = cStart + (cEnd - cStart) / 2;
            if (matrix[row][cMid] == target) {
                return true;
            }
            if (matrix[row][cMid] < target) {
                cStart = cMid + 1;
            } else {
                cEnd = cMid - 1;
            }
        }
        return false;
    }
}