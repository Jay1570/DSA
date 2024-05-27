package com.DSA.CustomCollections.BinaryTree.Problems;

//validate binary search tree
class Problem98 {
    public boolean isValidBST(TreeNode root) {
        return validate(root, null, null);
    }

    private boolean validate(TreeNode node, Integer low, Integer high) {
        if (node == null) {
            return true;
        }
        if ((low != null && node.val <= low) || (high != null && node.val >= high)) {
            return false;
        }
        return (validate(node.left, low, node.val) && validate(node.right, node.val, high));
    }
}
