package com.DSA.CustomCollections.BinaryTree.Problems;

//find kth smallest element in BST.(1-indexed)
class Problem230 {
    int n = 0;

    public int kthSmallest(TreeNode root, int k) {
        return smallest(root, k).val;
    }

    private TreeNode smallest(TreeNode node, int k) {
        if (node == null) {
            return null;
        }
        TreeNode left = smallest(node.left, k);
        if (left != null) {
            return left;
        }
        n++;
        if (n == k) {
            return node;
        }
        return smallest(node.right, k);
    }
}
