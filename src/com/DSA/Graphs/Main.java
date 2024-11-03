package com.DSA.Graphs;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Is it Directed Graph?(true/false) :- ");
        boolean directed = s.nextBoolean();
        System.out.print("Enter number of Edges :- ");
        int edges = s.nextInt();
        Graph<Integer> graph = new Graph<>(directed);
        System.out.println("Enter Edges :- ");
        for (int i = 0; i < edges; i++) {
            int u = s.nextInt();
            int v = s.nextInt();
            graph.addEdge(u, v);
        }
        graph.printAdjList();
        graph.bfs();
        System.out.println();
        graph.dfs();
        System.out.println();
        graph.detectCycle();
        graph.topologicalSort();
        System.out.print("Enter Source and Destination :- ");
        int source = s.nextInt();
        int destination = s.nextInt();
        graph.shortestPath(source, destination);
        s.close();
    }
}