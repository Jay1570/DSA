package com.DSA.Search;

import java.util.Arrays;

public class SortedMatrix {
    public static void main(String[] args) {
        int[][] arr = {
                {12,23,45,51,62},
                {68,71,79,85,88},
                {91,94,95,99,100},
                {103,107,110,115,119},
                {120,150,165,172,189},
        };
        System.out.println(Arrays.toString(search(arr, 189)));
    }
    static int[] search(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int rStart = 0;
        int rEnd = rows - 1;
        int cMid = cols / 2;

        if(rows == 1){
            return binarySearch(matrix, 0, 0, cols - 1, target);
        }

        while (rStart < (rEnd - 1)) {
            int mid = rStart + (rEnd - rStart) / 2;
            if (matrix[mid][cMid] == target) {
                return new int[]{mid, cMid};
            }
            if (matrix[mid][cMid] < target) {
                rStart = mid;
            } else {
                rEnd = mid;
            }
        }
        if (matrix[rStart][cMid] == target) {
            return new int[]{rStart, cMid};
        }

        if (matrix[rStart + 1][cMid] == target) {
            return new int[]{rStart + 1, cMid};
        }
        //search in 1st half
        if (target <= matrix[rStart][cMid - 1]) {
            return binarySearch(matrix, rStart, 0, cMid - 1, target);
        }
        //search un 2nd half
        if (target >= matrix[rStart][cMid + 1] && target <= matrix[rStart][cols - 1]) {
            return binarySearch(matrix, rStart, cMid + 1, cols - 1, target);
        }
        //search in 3rd half
        if (target <= matrix[rStart + 1][cMid-1]) {
            return binarySearch(matrix, rStart + 1, 0, cMid - 1, target);
        } else {
            return binarySearch(matrix, rStart + 1, cMid + 1, cols - 1, target);

        }
    }
    static int[] binarySearch(int[][] matrix, int row, int cStart, int cEnd, int target) {
        while (cStart <= cEnd) {
            int mid = cStart + (cEnd - cStart) / 2;
            if (target == matrix[row][mid]) {
                return new int[]{row, mid};
            }
            if (target < matrix[row][mid]) {
                cEnd = mid - 1;
            } else {
                cStart = mid + 1;
            }
        }
        return new int[]{-1, -1};
    }
}
