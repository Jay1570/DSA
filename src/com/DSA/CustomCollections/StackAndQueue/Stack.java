package com.DSA.CustomCollections.StackAndQueue;

public class Stack {
    protected int[] data;
    private static final int DEFAULT_SIZE = 10;

    private int top = -1;

    public Stack() {
        this(DEFAULT_SIZE);
    }

    public Stack(int size) {
        this.data = new int[size];
    }

    public boolean push (int item) {
        if(isFull()) {
            int[] temp = new int[data.length * 2];
            for (int i = 0; i < data.length; i++) {
                temp[i] = data[i];
            }
            data = temp;
        }
        top++;
        data[top] = item;
        return true;
    }

    public int peek () throws StackException {
        if (isEmpty()) {
            throw new StackException("Cannot peek from an Empty Stack");
        }
        return data[top];
    }

    public int pop () throws StackException {
        if (isEmpty()) {
            throw new StackException("Cannot pop from an Empty Stack");
        }
        int removed = data[top];
        top--;
        return removed;
    }

    public boolean isFull() {
        return top == data.length - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }
}
