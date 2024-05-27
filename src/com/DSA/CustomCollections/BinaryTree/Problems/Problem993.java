package com.DSA.CustomCollections.BinaryTree.Problems;

import java.util.LinkedList;
import java.util.Queue;

//check if x and y are cousins or not
class Problem993 {
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {

            int level = queue.size();
            boolean xFound = false;
            boolean yFound = false;

            for (int i = 0; i < level; i++) {
                TreeNode current = queue.poll();
                if (current.val == x) {
                    xFound = true;
                }
                if (current.val == y) {
                    yFound = true;
                }
                //check if x and y are siblings
                if (current.left != null && current.right != null) {
                    if ((current.left.val == x && current.right.val == y) || (current.left.val == y && current.right.val == x)) {
                        return false;
                    }
                }
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            if (xFound && yFound) {
                return true;
            }
            if (xFound || yFound) {
                return false;
            }
        }
        return false;
    }
}
