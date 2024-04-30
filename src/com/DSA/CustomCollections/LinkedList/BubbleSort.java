package com.DSA.CustomCollections.LinkedList;

public class BubbleSort {
    private Node head;  // points to the first node of List
    private Node tail;  // points to the last node of List

    private int size;

    public BubbleSort() {
        this.size = 0;
    }

    public void insertFirst(int data) {
        Node node = new Node(data);
        node.next = head;
        head = node;
        if (tail == null) {
            tail = head;
        }
        size++;
    }

    public void insertLast(int data) {
        if (tail == null) {
            insertFirst(data);
            return;
        }
        Node node = new Node(data);
        tail.next = node;
        tail = node;
        size++;
    }

    public void insert(int data, int index) {
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

    public Object deleteFirst() {
        if (head == null) {
            return "List is empty";
        }
        int data = head.data;
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
        int data = tail.data;
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
        int data = prev.next.data;
        prev.next = prev.next.next;
        size--;
        return data;
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

    public void bubbleSort() {
        bubbleSort(size - 1, 0);
    }

    private void bubbleSort(int row, int col) {
        if(row == 0) {
            return;
        }
        if(col < row) {
            Node first = get(col);
            Node second = get(col + 1);

            if (first.data > second.data) {
                //swap
                if(first == head) {
                    head =  second;
                    first.next = second.next;
                    second.next = first;
                } else if (second == tail) {
                    Node prev = get(col - 1);
                    prev.next = second;
                    tail = first;
                    first.next = null;
                    second.next = tail;
                } else {
                    Node prev = get(col - 1);
                    prev.next = second;
                    first.next = second.next;
                    second.next = first;
                }
            }
            bubbleSort(row, col + 1);
        } else {
            bubbleSort(row - 1, 0);
        }
    }

    public void mergeSort() {
        head = sortList(head);
        tail = get(size - 1);
    }

    private Node sortList(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node mid = getMid(head);
        Node left = sortList(head);
        Node right = sortList(mid);

        return merge(left, right);
    }

    private Node merge(Node l, Node r) {
        Node ans = new Node();
        Node temp = ans;
        while (l != null && r != null) {
            if (l.data < r.data) {
                temp.next = l;
                l = l.next;
                temp = temp.next;
            } else {
                temp.next = r;
                r = r.next;
                temp = temp.next;
            }
        }
        temp.next = (l != null) ? l : r;
        return ans.next;
    }

    private Node getMid(Node head) {
        Node midPrev = null;
        while (head != null && head.next != null) {
            midPrev = (midPrev == null) ? head : midPrev.next;
            head = head.next.next;
        }
        Node mid = midPrev.next;
        midPrev.next = null;
        return mid;
    }

    //recursive reverse
    public void reverse() {
        reverse(head);
    }

    private void reverse(Node node) {
        if (node == tail) {
            head = tail;
            return;
        }

        reverse(node.next);

        tail.next = node;
        tail = node;
        tail.next = null;
    }

    public void reverseIterative() {
        head = reverseIterative(head);
        tail = get(size - 1);
    }
    //iterative reverse
    private Node reverseIterative(Node head) {
        if(head == null || head.next == null) {
            return head;
        }

        Node prev = null;
        Node pres = head;
        Node next = pres.next;

        while (pres != null) {
            pres.next = prev;
            prev = pres;
            pres = next;
            if (next != null){
                next = next.next;
            }
        }
        return prev;
    }
    public boolean isPalindrome() {
        boolean ans = isPalindrome(head);
        return ans;
    }
    private boolean isPalindrome(Node head) {
        Node mid = getMid(head);
        Node secondHalf = mid.next;
        mid.next = null;
        Node head2 = reverseIterative(secondHalf);
        Node temp = head;
        Node temp2 = head2;
        boolean is = true;
        // compare both the halves
        while (temp != null && temp2 != null) {
            if (temp.data != temp2.data) {
                is = false;
                break;
            }
            temp = temp.next;
            temp2 = temp2.next;
        }
        reverseIterative(head2);
        mid.next = secondHalf;
        return is;
    }

    public void reverseBetween(int left, int right) {
        head = reverseBetween(head, left, right);
        tail = get(size - 1);
    }

    private Node reverseBetween(Node head, int left, int right) {
        if (left == right) {
            return head;
        }
        //skip left - 1 nodes
        Node current = head;
        Node prev = null;

        for (int i = 0; current != null && i < left - 1; i++) {
            prev = current;
            current = current.next;
        }
        Node last = prev;
        Node newEnd = current;
        Node next = current.next;
        // reverse between left and right
        for (int i = 0; current != null && i < right - left + 1; i++) {
            current.next = prev;
            prev = current;
            current = next;
            if (next != null){
                next = next.next;
            }
        }
        if(last != null) {
            last.next = prev;
        } else {
            head = prev;
        }
        newEnd.next = current;
        return head;
    }

    class Node {
        private int data;
        private Node next;

        public Node() {
        }

        public Node(int data) {
            this.data = data;
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        BubbleSort list = new BubbleSort();
        BubbleSort list2 = new BubbleSort();
        list.insertLast(12);
        list.insertLast(10);
        list.insertLast(20);
        list.insertLast(21);
        list.insertLast(12);
        list.insertLast(15);
        list.insertLast(9);
        list.display();
        list.bubbleSort();
        list.display();
        list.insertLast(2);
        list.insertLast(6);
        list.insertFirst(50);
        list.display();
        list.mergeSort();
        list.display();
        list.reverse();
        list.display();
        list.reverseIterative();
        list.display();
        list.reverseBetween(4, 9);
        list.display();
        list2.insertLast(1);
        list2.insertLast(2);
        list2.insertLast(3);
        list2.insertLast(2);
        list2.insertLast(1);
        System.out.println(list2.isPalindrome());
        list2.display();
    }
}