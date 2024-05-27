package com.DSA.CustomCollections.BinaryTree.Problems;

//convert given tree into LinkedList in preOrder form in a way that all the left nodes of the tree will be null
class Problem114 {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode current = root;
        while (current != null) {
            if (current.left != null) {
                TreeNode temp = current.left;
                while (temp.right != null) {
                    temp = temp.right;
                }
                temp.right = current.right;
                current.right = current.left;
                current.left = null;
            }
            current = current.right;
        }
    }
}
