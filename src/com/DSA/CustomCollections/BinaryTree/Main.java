package com.DSA.CustomCollections.BinaryTree;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //binaryTree();
        //binarySearchTree();
        avl();
        //segmentTree();
    }
    static void binaryTree () {
        BinaryTree tree = new BinaryTree();
        Scanner s = new Scanner(System.in);
        tree.populate(s);
        tree.display();
        tree.preOrder();
        System.out.println();
        tree.inOrder();
        System.out.println();
        tree.postOrder();

    }
    static void binarySearchTree () {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(new int[]{15,21,12,5,22,18,6,32,14,4,19,17,13,32,11,10});
        bst.display();
        System.out.println(bst.balanced());
    }
    static void avl() {
        AVL bst = new AVL();
        for (int i = 1; i <= 17; i++) {
            bst.insert(i);
        }
        bst.display();
        bst.preOrder();
        System.out.println(bst.height());
        System.out.println(bst.balanced());
        bst.bfs();
    }
    static void segmentTree() {
        int[] arr = {3,8,6,7,-2,-8,4,9};
        SegmentTree tree = new SegmentTree(arr);
        tree.preOrder();
        System.out.println();
        System.out.println(tree.query(1,5));
        tree.update(3, 10);
        tree.preOrder();
    }
}