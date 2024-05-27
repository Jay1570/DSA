package com.DSA.CustomCollections.BinaryTree.Problems;

//invert given tree.for example swap all the left side of nodes with right side of nodes
class Problem226 {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        root.left = right;
        root.right = left;

        return root;
    }
}
