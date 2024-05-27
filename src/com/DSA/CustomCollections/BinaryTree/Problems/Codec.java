package com.DSA.CustomCollections.BinaryTree.Problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//problem 297. serialize and deserialize binary tree
class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<String> list = new ArrayList<>();
        helper(root, list);
        return String.join(",", list);
    }

    private void helper(TreeNode node, List<String> list) {
        if (node == null) {
            list.add("null");
            return;
        }
        list.add(String.valueOf(node.val));
        helper(node.left, list);
        helper(node.right, list);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        List<String> list = new ArrayList<>(Arrays.asList(data.split(",")));
        return helper2(list);
    }

    private TreeNode helper2(List<String> list) {
        if (list.get(0).equals("null")) {
            list.remove(0);
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(list.remove(0)));
        node.left = helper2(list);
        node.right = helper2(list);
        return node;
    }
}
