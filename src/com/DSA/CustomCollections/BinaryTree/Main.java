package com.DSA.CustomCollections.BinaryTree;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //binaryTree();
        binarySearchTree();
        //avl();
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
        bst.insert(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17});
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
        System.out.println(bst.diameterOfBinaryTree());
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