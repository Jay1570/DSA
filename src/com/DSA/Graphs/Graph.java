package com.DSA.Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Graph<T> {

    private HashMap<T, List<T>> adjacencyList = new HashMap<>();

    public void addEdge(T u, T v, boolean direction) {
        //direction = true -> directed graph
        //direction = false -> undirected graph

        List<T> listOfU = adjacencyList.getOrDefault(u, new ArrayList<>());
        listOfU.addLast(v);
        adjacencyList.put(u, listOfU);

        if (!direction) {
            List<T> listOfV = adjacencyList.getOrDefault(v, new ArrayList<>());
            listOfV.addLast(u);
            adjacencyList.put(v, listOfV);
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
}