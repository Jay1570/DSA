package com.DSA.CustomCollections.BinaryTree.Problems;

//return longest possible path from tree. it may or may not pass through root node
class Problem543 {
    int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        height(root);
        return diameter - 1;
    }

    int height(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = height(node.left);
        int right = height(node.right);
        diameter = Math.max(diameter, left + right + 1);
        return (Math.max(left, right) + 1);
    }
}
