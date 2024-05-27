package com.DSA.CustomCollections.BinaryTree.Problems;

//maximum path sum
class Problem124 {
    int sum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        helper(root);
        return sum;
    }

    int helper(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = maxPathSum(node.left);
        int right = maxPathSum(node.right);

        left = Math.max(0, left);
        right = Math.max(0, right);

        int addition = left + right + node.val;
        sum = Math.max(sum, addition);
        return Math.max(left, right) + node.val;
    }
}
