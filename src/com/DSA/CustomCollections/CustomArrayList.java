package com.DSA.CustomCollections;

import java.util.Arrays;

// new is "<T>". This is called generics or type erasure in java
public class CustomArrayList<T> {
    private Object[] data;
    private static final int DEFAULT_SIZE = 10;
    private int size = 0;

    public CustomArrayList() {
        this.data = new Object[DEFAULT_SIZE];
    }

    public void add(T data) {
        if(this.isFull()) {
            resize();
        }
        this.data[size++] = data;
    }

    private void resize() {
        Object[] temp = new Object[data.length * 2];
        // copy the elements in new array
        for (int i = 0; i < data.length; i++) {
            temp[i] = data[i];
        }
        data = temp;
    }

    private boolean isFull() {
        return size == data.length;
    }

    public T remove() {
        return (T) data[--size];
    }

    public T get (int index) {
        return (T)data[index];
    }

    public int size() {
        return size;
    }

    public void set (int index, T data) {
        this.data[index] = data;
    }

    @Override
    public String toString() {
        return "CustomArrayList{data=" + Arrays.toString(data) + ", size=" + size + "}";
    }

    public static void main(String[] args) {
        CustomArrayList<Integer> list = new CustomArrayList<>();

        for (int i = 1; i < 18; i++) {
            list.add(i);
        }
        System.out.println(list.remove());
        System.out.println(list.get(12));
        System.out.println(list.size());
        list.set(11, 90);
        System.out.println(list);
    }
}