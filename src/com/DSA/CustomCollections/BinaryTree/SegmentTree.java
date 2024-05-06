package com.DSA.CustomCollections.BinaryTree;

public class SegmentTree {

    Node root;

    public SegmentTree(int[] arr) {
        this.root = constructTree(arr, 0, arr.length - 1);
    }

    private Node constructTree(int[] arr,int start, int end) {
        if (start == end) {
            Node leaf = new Node(arr[start],start, end);
            return leaf;
        }
        Node node = new Node(start, end);
        int mid = start + (end - start) / 2;
        node.left = constructTree(arr, start, mid);
        node.right = constructTree(arr, mid + 1, end);
        node.data = node.left.data + node.right.data;
        return node;
    }

    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data + "[" + node.start + "," + node.end + "] ");
        preOrder(node.left);
        preOrder(node.right);
    }

    public int query (int start, int end) {
        return query(root, start, end);
    }
    private int query (Node node, int start, int end) {
        if (node.start >= start && node.end <= end) {
            return node.data;
        } else if (node.start > end || node.end < start) {
            return 0;
        } else {
            return query(node.left, start, end) + query(node.right, start, end);
        }
    }

    public void update (int index, int data) {
        root.data = update (root, index, data);
    }

    private int update (Node node, int index, int data) {
        if (index >= node.start && index <= node.end) {
            if (index == node.start && index == node.end) {
                node.data = data;
                return node.data;
            } else {
                node.data = update(node.left, index, data) + update(node.right, index, data);
                return node.data;
            }
        }
        return node.data;
    }

    public static class Node {
         private int data;
         private int start;
         private int end;
         Node left;
         Node right;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public Node(int data, int start, int end) {
            this.data = data;
            this.start = start;
            this.end = end;
        }
    }
}
