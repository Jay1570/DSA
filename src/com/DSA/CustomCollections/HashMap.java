package com.DSA.CustomCollections;

import java.util.ArrayList;
import java.util.LinkedList;

public class HashMap<K, V> {

    ArrayList<LinkedList<Entity>> list;

    private int size = 0;

    private float load = 0.5f;

    public HashMap() {
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new LinkedList<>());
        }
    }

    public void put(K key, V value) {
        int hash = Math.abs(key.hashCode() % list.size());
        LinkedList<Entity> entities = list.get(hash);
        for (Entity entity : entities) {
            if (entity.key.equals(key)) {
                entity.value = value;
                return;
            }
        }
        if ((float)size / list.size() > load) {
            reHash();
        }
        entities.add(new Entity(key, value));
        size++;
    }

    private void reHash() {
        ArrayList<LinkedList<Entity>> old = list;
        list = new ArrayList<>();
        size = 0;

        for (int i = 0; i < old.size()*2; i++) {
            list.add(new LinkedList<>());
        }

        for (LinkedList<Entity> entries : old) {
            for (Entity entry : entries) {
                put(entry.key, entry.value);
            }
        }
    }

    public V get(K key) {
        int hash = Math.abs(key.hashCode() % list.size());
        LinkedList<Entity> entities = list.get(hash);
        for (Entity entity : entities) {
            if (entity.key.equals(key)) {
                return entity.value;
            }
        }
        return null;
    }

    public void remove(K key) {
        int hash = Math.abs(key.hashCode() % list.size());
        LinkedList<Entity> entities = list.get(hash);
        Entity target = null;
        for (Entity entity : entities) {
            if (entity.key.equals(key)) {
                target = entity;
                break;
            }
        }
        entities.remove(target);
        size--;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    //for println function
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        for (LinkedList<Entity> entities : list) {
            for (Entity entity : entities) {
                builder.append(entity.key);
                builder.append("=");
                builder.append(entity.value);
                builder.append(",");
            }
        }
        if (builder.length() > 1) {
            builder.setLength(builder.length() - 1);
        }
        builder.append("}");
        return builder.toString();
    }

    private class Entity {
        K key;
        V value;

        public Entity(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Jay",99);
        map.put("Vivek",12);
        map.put("Parth", null);

        System.out.println(map);
    }
}
