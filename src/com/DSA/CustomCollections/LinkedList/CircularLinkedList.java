package com.DSA.CustomCollections.LinkedList;

public class CircularLinkedList<T> {

    private Node head;
    private Node tail;

    private int size;

    public void insert(T data) {
        Node node = new Node(data);
        if(head == null) {
            head = node;
            tail = node;
            return;
        }
        tail.next = node;
        node.next = head;
        tail = node;
        size++;
    }

    public void delete (T value) {
        Node node = head;
        if (node == null) {
            System.out.println("List is empty");
            return;
        }
        if (node.data == value) {
            head = head.next;
            tail.next = head;
            return;
        }
        do {
            Node n = node.next;
            if(n.data ==  value) {
                node.next = n.next;
                break;
            }
            node = node.next;
        } while (node != head);
    }

    public void display() {
        Node temp = head;
        if(head == null) {
            System.out.println("List is empty");
            return;
        }
        do {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }while (temp != head);
        System.out.println("HEAD");
    }

    private class Node {
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

}
