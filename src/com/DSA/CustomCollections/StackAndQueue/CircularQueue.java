package com.DSA.CustomCollections.StackAndQueue;

public class CircularQueue {
    protected int[] data;
    private static final int DEFAULT_SIZE = 10;

    private int end = 0;
    private int front = 0;
    private int size = 0;

    public CircularQueue() {
        this(DEFAULT_SIZE);
    }

    public CircularQueue(int size) {
        this.data = new int[size];
    }

    public boolean insert (int item) {
        if(isFull()) {
            resize();
        }
        data[end++] = item;
        end = end % data.length;
        size++;
        return true;
    }

    public int remove () throws Exception {
        if (isEmpty()) {
            throw new Exception("Queue is Empty!!");
        }
        int removed = data[front++];
        front = front % data.length;
        size--;
        return removed;
    }

    public int front () throws Exception {
        if (isEmpty()) {
            throw new Exception("Queue is Empty!!");
        }
        return data[front];
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is Empty!!");
            return;
        }
        int i = front;
        do {
            System.out.print(data[i] + " ");
            i++;
            i %= data.length;
        } while (i != end);
        System.out.println();
    }

    private void resize() {
        int[] temp = new int[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            temp[i] = data[(front+i) % data.length];
        }
        front = 0;
        end = data.length;
        data = temp;
    }

    public boolean isFull() {
        return size == data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
