package com.DSA.Graphs;

import java.util.Scanner;

public class MainWeighted {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Is it Directed Graph?(true/false) :- ");
        boolean directed = s.nextBoolean();
        System.out.print("Enter number of Edges :- ");
        int edges = s.nextInt();
        WeightedGraph<Integer> graph = new WeightedGraph<>(directed);
        System.out.println("Enter Edges(u v weight) :- ");
        for (int i = 0; i < edges; i++) {
            int u = s.nextInt();
            int v = s.nextInt();
            int weight = s.nextInt();
            graph.addEdge(u, v, weight);
        }
        graph.printAdjList();
        graph.bfs();
        System.out.println();
        graph.dfs();
        System.out.println();
        graph.detectCycle();
        graph.topologicalSort();
        System.out.print("Enter Source :- ");
        int source = s.nextInt();
        graph.shortestPathToAllNodeFromSource(source);
        s.close();
    }
}
