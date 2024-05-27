package com.DSA.CustomCollections.BinaryTree.Problems;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

class Additional {
    boolean findPath(TreeNode node, int[] arr) {
        if (node == null) {
            return arr.length == 0;
        }
        return helper(node, arr, 0);
    }

    private boolean helper(TreeNode node, int[] arr, int index) {
        if (node == null) {
            return false;
        }
        if (index >= arr.length || node.val == arr[index]) {
            return false;
        }
        if (node.left == null && node.right == null && index == arr.length - 1) {
            return true;
        }
        return helper(node.left, arr, index + 1) || helper(node.right, arr, index + 1);
    }

    int countPaths(TreeNode node, int sum) {
        List<Integer> path = new LinkedList<>();
        return helper(node, sum, path);
    }

    int helper(TreeNode node, int sum, List<Integer> path) {
        if (node == null) {
            return 0;
        }
        path.add(node.val);
        int count = 0;
        int s = 0;
        ListIterator<Integer> itr = path.listIterator(path.size());
        while (itr.hasPrevious()) {
            s += itr.previous();
            if (s == sum) {
                count++;
            }
        }

        count += helper(node.left, sum, path) + helper(node.right, sum, path);
        path.remove(path.size() - 1);
        return count;
    }
}
