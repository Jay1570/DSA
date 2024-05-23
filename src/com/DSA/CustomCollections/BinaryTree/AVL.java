package com.DSA.CustomCollections.BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AVL {

    private Node root;

    public AVL() {
    }

    public int height() {
        return height(root);
    }

    private int height (Node node) {
        if(node == null) {
            return -1;
        }
        return node.height;
    }

    public boolean isEmpty () {
        return root == null;
    }

    public void preOrder () {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data + " -> ");
        preOrder(node.left);
        preOrder(node.right);
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
        node.height = Math.max(height(node.left), height(node.right)) + 1;

        return rotate(node, data);
    }

    private Node rotate(Node node, int data) {
        int diff = height(node.left) - height(node.right);
        if(diff >1) {
            //left heavy
            int diffLeft = height(node.left.left) - height(node.left.right);
            if (diffLeft > 0) {
                //left-left case
                return rightRotate(node);
            }
            if (diffLeft < 0) {
                //left-right case
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }
        if (diff < -1) {
            //right heavy
            int diffRight = height(node.right.left) - height(node.right.right);
            if (diffRight < 0) {
                //right-right case
                return leftRotate(node);
            }
            if (diffRight > 0) {
                //right-left case
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }
        return node;
    }

    private Node leftRotate(Node p) {
        Node c = p.right;
        Node t = c.left;

        c.left = p;
        p.right = t;

        p.height = Math.max(height(p.left),height(p.right)) + 1;
        c.height = Math.max(height(c.left),height(c.right)) + 1;

        return c;
    }

    private Node rightRotate(Node p) {
        Node c = p.left;
        Node t = c.right;

        c.right = p;
        p.left = t;

        p.height = Math.max(height(p.left),height(p.right)) + 1;
        c.height = Math.max(height(c.left),height(c.right)) + 1;

        return c;
    }

    public void insert (int[] data) {
        for (int val : data) {
            root = insert(val, root);
        }
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

    public void bfs() {
        System.out.println(bfs(root));
    }

    //my implementation
    private void bfs1(Node node, Queue<Node> queue) {
        System.out.print(node.data + " -> ");

        if (node.left != null){
            queue.add(node.left);
        }
        if (node.right != null){
            queue.add(node.right);
        }
        if (!queue.isEmpty())
            bfs1(queue.remove(), queue);
    }

    //better version
    private List<List<Integer>> bfs(Node node) {
        if (node == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        while(!queue.isEmpty()) {
            int level = queue.size();
            List<Integer> current= new ArrayList<>(level);
            for (int i = 0; i < level; i++) {
                Node currentNode = queue.poll();
                current.add(currentNode.data);
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            result.add(current);
        }
        return result;
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
