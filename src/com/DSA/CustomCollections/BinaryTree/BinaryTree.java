package com.DSA.CustomCollections.BinaryTree;

import java.util.Scanner;

//this is basic binary tree
public class BinaryTree {

    private Node root;
    static int count = 10;

    public BinaryTree() {
    }

    public void populate(Scanner scanner) {
        System.out.println("Enter the root Node value :- ");
        int data = scanner.nextInt();
        root = new Node(data);
        populate(scanner, root);
    }

    private void populate(Scanner scanner, Node node) {
        System.out.println("Do you want to enter Left of (true/false) " + node.data + " :-");
        boolean l = scanner.nextBoolean();
        if (l) {
            System.out.println("Enter the value of the Left of " + node.data + " :-");
            int data = scanner.nextInt();
            node.left = new Node(data);
            populate(scanner, node.left);
        }
        System.out.println("Do you want to enter Right of (true/false) " + node.data + " :-");
        boolean r = scanner.nextBoolean();
        if (r) {
            System.out.println("Enter the value of the Right of  " + node.data + " :-");
            int data = scanner.nextInt();
            node.right = new Node(data);
            populate(scanner, node.right);
        }
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

    public void inOrder () {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.data + " -> ");
        inOrder(node.right);
    }

    public void postOrder () {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data + " -> ");
    }

    private static class Node {
        int data;
        Node left;
        Node right;
        public Node (int data) {
            this.data = data;
        }
    }
}
