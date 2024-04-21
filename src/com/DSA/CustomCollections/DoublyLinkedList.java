package com.DSA.CustomCollections;

public class DoublyLinkedList<T> {

    private Node head;

    private int size;
/*
    public DoublyLinkedList() {
        this.size = 0;
    }
*/

    public void insertFirst(T data) {
        Node node = new Node(data);
        node.next = head;
        node.prev = null;
        if(head != null) {
            head.prev = node;
        }
        head = node;
        size++;
    }

    public void insertLast(T data) {
        if(head == null) {
            insertFirst(data);
            return;
        }
        Node node = new Node(data);
        Node last = getNode(size - 1);
        node.prev = last;
        last.next = node;
        node.next = null;
        size++;
    }

    public void insert(T data, int index) {
        if (index < 0 || index > size) {
            System.out.println("Invalid index...");
            return;
        }
        if (index == 0) {
            insertFirst(data);
            return;
        }
        if(index == size) {
            insertLast(data);
            return;
        }
        Node last = getNode(index - 1);
        Node node = new Node(data, last.next, last);
        last.next = node;
        node.next.prev = node;
        size++;
    }

    public void insert(T data, T after) {
        Node p = find(after);
        if(p == null) {
            System.out.println("Given node does not exist");
            return;
        }
        Node node = new Node(data);
        node.next = p.next;
        node.prev = p;
        p.next = node;
        if(node.next != null) {
            node.next.prev = node;
        }
        size++;
    }

    public Object deleteFirst () {
        if(head == null) {
            return "List is empty";
        }
        T val = head.data;
        head = head.next;
        if(head !=  null) {
            head.prev = null;
        }
        size--;
        return val;
    }

    public Object deleteLast () {
        if(head == null) {
            return "List is empty";
        }
        if(size <= 1) {
            return deleteFirst();
        }
        Node node = getNode(size - 2);
        T val = node.next.data;
        node.next = null;
        size--;
        return val;
    }

    public Object delete (int index) {
        if (index < 0 || index > size - 1) {
            return "Invalid index...";
        }
        if(head == null) {
            return "List is empty";
        }
        if (index == 0) {
            return deleteFirst();
        }
        if (index == size - 1) {
            return deleteLast();
        }
        Node node = getNode(index - 1);
        T val = node.next.data;
        node.next = node.next.next;
        node.next.prev = node;
        size--;
        return val;
    }

    private Node find (T data) {
        Node temp = head;
        while (temp != null) {
            if (temp.data == data) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    private Node getNode(int index) {
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    public void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " <--> ");
            temp = temp.next;
        }
        System.out.println("END");
    }

    public void displayRev() {
        Node temp = getNode(size - 1);
        while (temp != null) {
            System.out.print(temp.data + " <--> ");
            temp = temp.prev;
        }
        System.out.println("Start");
    }

    private class Node  {
        private T data;
        private Node next;
        private Node prev;

        public Node(T data) {
            this.data = data;
        }

        public Node(T data, Node next, Node prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }
}
