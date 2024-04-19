package com.DSA.CustomCollections;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.insertFirst(12);
        list.insertFirst(10);
        list.insertFirst(23);
        list.insertLast(12);
        list.insertLast(90);
        list.insertFirst(20);
        list.insertFirst(21);
        list.insert(100, 4);
        list.display();
        System.out.println(list.deleteFirst());
        list.display();
        System.out.println(list.deleteLast());
        list.display();
        System.out.println(list.delete(3));
        list.display();
        System.out.println(list.getValue(2));
    }
}
