package com.DSA.Graphs;

import java.util.*;

class Graph<T> {

    private final HashMap<T, List<T>> adjacencyList = new HashMap<>();

    public void addEdge(T u, T v, boolean direction) {
        //direction = true -> directed graph
        //direction = false -> undirected graph

        adjacencyList.putIfAbsent(u, new ArrayList<>());
        adjacencyList.get(u).add(v);

        if (!direction) {
            adjacencyList.putIfAbsent(v, new ArrayList<>());
            adjacencyList.get(v).add(u);
        }
    }

    public void printAdjList() {
        for (T key : adjacencyList.keySet()) {
            System.out.print(key + " -> ");
            for (T edge : adjacencyList.get(key)) {
                System.out.print(edge + ", ");
            }
            System.out.println();
        }
    }

    public void bfs() {
        HashSet<T> visited = new HashSet<>();

        for(T key : adjacencyList.keySet()) {
            if (!visited.contains(key)) {
                bfs(key, visited);
            }
        }
    }

    private void bfs(T node, HashSet<T> visited) {
        Queue<T> queue = new LinkedList<>();
        queue.add(node);
        visited.add(node);
        while (!queue.isEmpty()) {
            T front = queue.poll();
            System.out.print(front + ", ");

            for (T neighbours : adjacencyList.getOrDefault(front, new ArrayList<>())) {
                if(!visited.contains(neighbours)) {
                    queue.add(neighbours);
                    visited.add(neighbours);
                }
            }
        }
    }

    public void dfs() {
        HashSet<T> visited = new HashSet<>();

        for (T key: adjacencyList.keySet()) {
            if (!visited.contains(key)) {
                dfs(key, visited);
            }
        }
    }

    private void dfs(T node, HashSet<T> visited) {
        System.out.print(node + ", ");
        visited.add(node);

        for (T neighbours : adjacencyList.get(node)) {
            if (!visited.contains(neighbours)) dfs(neighbours, visited);
        }
    }
}