package com.DSA.CustomCollections;

import com.DSA.CustomCollections.LinkedList.CircularLinkedList;
import com.DSA.CustomCollections.LinkedList.DoublyLinkedList;
import com.DSA.CustomCollections.LinkedList.SinglyLinkedList;
import com.DSA.CustomCollections.StackAndQueue.CircularQueue;
import com.DSA.CustomCollections.StackAndQueue.Queue;
import com.DSA.CustomCollections.StackAndQueue.Stack;

public class Main {

    public static void main(String[] args) throws Exception {
        singlyLinkedList();
        doublyLinkedList();
        circularLinkedList();
        stack();
        queue();
        circularQueue();
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
    static void circularLinkedList() {
        CircularLinkedList<Integer> list = new CircularLinkedList<>();
        list.insert(12);
        list.insert(17);
        list.insert(34);
        list.insert(10);
        list.display();
    }
    static void stack () throws Exception {
        Stack s = new Stack(5);
        s.push(12);
        s.push(21);
        s.push(7);
        s.push(1);
        s.push(34);
        s.push(32);
        System.out.println(s.pop());
        System.out.println(s.pop());
        System.out.println(s.pop());
        System.out.println(s.pop());
        System.out.println(s.pop());
    }
    static void queue () throws Exception {
        Queue queue = new Queue(5);
        queue.insert(12);
        queue.insert(10);
        queue.insert(90);
        queue.display();
        System.out.println(queue.remove());
        queue.display();
    }
    static void circularQueue () throws Exception {
        CircularQueue queue = new CircularQueue(5);
        queue.insert(12);
        queue.insert(10);
        queue.insert(90);
        queue.insert(12);
        queue.insert(34);
        queue.display();
        System.out.println(queue.remove());
        queue.display();
        System.out.println(queue.remove());
        queue.display();
        queue.insert(21);
        queue.insert(56);
        queue.insert(90);
        queue.display();
    }
}
