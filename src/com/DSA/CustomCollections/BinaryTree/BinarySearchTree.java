package com.DSA.CustomCollections.BinaryTree;

public class BinarySearchTree {

    private Node root;

    public BinarySearchTree() {
    }

    public int height (Node node) {
        if(node == null) {
            return -1;
        }
        return node.height;
    }

    public boolean isEmpty () {
        return root == null;
    }

    public void display () {
        display(root, 0);
    }

    private void display (Node node, int level) {
        if (node == null) {
            return;
        }
        display(node.right, level + 1);
        if (level != 0) {
            for (int i = 0; i < level-1; i++) {
                System.out.print("|\t\t");
            }
            System.out.println("|----->" + node.data);
        } else {
            System.out.println(node.data);
        }
        display(node.left, level + 1);
    }

    public void insert (int data) {
        root = insert(data, root);
    }

    private Node insert (int data, Node node) {
        if (node == null) {
            node = new Node(data);
            return node;
        }
        if (data < node.data) {
            node.left = insert(data, node.left);
        }
        if (data > node.data) {
            node.right = insert(data, node.right);
        }
        node.height = max(height(node.left), height(node.right)) + 1;
        return node;
    }

    public void insert(int[] nums) {
        if(nums == null || nums.length == 0) {
            return;
        }
        root = insert (nums, 0, nums.length - 1);
    }
    private Node insert (int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = start + (end - start) / 2;
        Node root = new Node(nums[mid]);
        root.left = insert(nums, start, mid - 1);
        root.right = insert(nums, mid + 1, end);
        return root;
    }


    public boolean balanced () {
        return balanced(root);
    }

    private boolean balanced(Node node) {
        if (node == null) {
            return true;
        }
        return Math.abs(height(node.left) - height(node.right)) <= 1 && balanced(node.left) && balanced(node.right);
    }

    private int max (int a, int b) {
        return (a > b) ? a : b;
    }

    public static class Node {
        private int data;
        private int height;
        private Node left;
        private Node right;
        public Node (int data) {
            this.data = data;
        }
        public int getValue() {
            return data;
        }
    }
}
