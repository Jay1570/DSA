package com.DSA.CustomCollections;

public class Main {
    public static void main(String[] args) {
        singlyLinkedList();
        doublyLinkedList();
    }
    static void singlyLinkedList() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.insertFirst(12);
        list.insertFirst(10);
        list.insertFirst(23);
        list.insertLast(12);
        list.insertLast(90);
        list.insertFirst(20);
        list.insertFirst(21);
        list.display();
        list.insert(100, 4);
        list.display();
        System.out.println(list.deleteFirst());
        list.display();
        System.out.println(list.deleteLast());
        list.display();
        System.out.println(list.delete(3));
        list.display();
        System.out.println(list.getValue(4));
    }
    static void doublyLinkedList() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        list.insertLast("Maan");
        list.insertFirst("Vivek");
        list.insertFirst("Parth");
        list.insertFirst("Rishi");
        list.insertFirst("Shravan");
        list.insertLast("Dev");
        list.display();
        list.displayRev();
        list.insert("Siddharth", 6);
        list.display();
        list.insert("Jay", "Rishi");
        list.display();
        list.insertLast("Pratik");
        list.display();
        System.out.println(list.deleteFirst());
        list.display();
        System.out.println(list.deleteLast());
        list.display();
        System.out.println(list.delete(1));
        list.display();
        list.displayRev();
    }
}
