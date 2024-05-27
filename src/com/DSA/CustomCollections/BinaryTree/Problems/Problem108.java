package com.DSA.CustomCollections.BinaryTree.Problems;

//insert a sorted array in binary tree in a way that tree is balanced
class Problem108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return insert(nums, 0, nums.length - 1);
    }

    private TreeNode insert(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = insert(nums, start, mid - 1);
        root.right = insert(nums, mid + 1, end);
        return root;
    }
}
