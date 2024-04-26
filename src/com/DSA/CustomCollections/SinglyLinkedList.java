package com.DSA.CustomCollections;

import java.util.LinkedList;

public class SinglyLinkedList<T> {

    private Node head;  // points to the first node of List
    private Node tail;  // points to the last node of List

    private int size;

    public SinglyLinkedList() {
        this.size = 0;
    }

    public void insertFirst(T data) {
        Node node = new Node(data);
        node.next = head;
        head = node;
        if (tail == null) {
            tail = head;
        }
        size++;
    }

    public void insertLast(T data) {
        if (tail == null) {
            insertFirst(data);
            return;
        }
        Node node = new Node(data);
        tail.next = node;
        tail = node;
        size++;
    }

    public void insert(T data, int index) {
        if (index < 0 || index > size - 1) {
            System.out.println("Invalid index...");
            return;
        }
        if (index == 0) {
            insertFirst(data);
            return;
        }
        if (index == size) {
            insertLast(data);
            return;
        }
        Node temp = get(index - 1);
        Node node = new Node(data, temp.next);
        temp.next = node;
        size++;
    }

    public void insertRec(T data, int index) {
        head = insertRec(index, data, head);
    }

    private Node insertRec(int index, T data, Node node) {
        if (index == 0) {
            Node temp = new Node(data, node);
            size++;
            return temp;
        }
        node.next = insertRec(index - 1, data, node.next);
        return node;
    }

    public Object deleteFirst() {
        if (head == null) {
            return "List is empty";
        }
        T data = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return data;
    }

    public Object deleteLast() {
        if (head == null) {
            return "List is empty";
        }
        if (size <= 1) {
            return deleteFirst();
        }
        Node secondLast = get(size - 2);
        T data = tail.data;
        tail = secondLast;
        tail.next = null;
        size--;
        return data;
    }

    public Object delete(int index) {
        if (index < 0 || index > size - 1) {
            return "Invalid index...";
        }
        if (head == null) {
            return "List is empty";
        }
        if (index == 0) {
            return deleteFirst();
        }
        if (index == size - 1) {
            return deleteLast();
        }
        Node prev = get(index - 1);
        T data = prev.next.data;
        prev.next = prev.next.next;
        size--;
        return data;
    }

    public T getValue(int index) {
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.data;
    }

    private Node get(int index) {
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    public void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("END");
    }

    class Node {
        private T data;
        private Node next;

        public Node(T data) {
            this.data = data;
        }

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    // questions
    //problem83
    public void duplicates() {
        Node node = head;
        while (node.next != null) {
            if (node.data == node.next.data) {
                node.next = node.next.next;
                size--;
            } else {
                node = node.next;
            }
        }
        tail = node;
        tail.next = null;
    }
}