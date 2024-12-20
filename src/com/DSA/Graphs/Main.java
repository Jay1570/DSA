package com.DSA.Graphs;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Is it Weighted Graph?(true/false) :- ");
        boolean isWeighted = s.nextBoolean();
        System.out.print("Is it Directed Graph?(true/false) :- ");
        boolean directed = s.nextBoolean();
        System.out.print("Enter number of Edges :- ");
        int edges = s.nextInt();
        Graph<Integer> graph = new Graph<>(directed);
        if (isWeighted) {
            System.out.println("Enter Edges(u v weight) :- ");
        } else {
            System.out.println("Enter Edges(u v) :- ");
        }
        for (int i = 0; i < edges; i++) {
            int u = s.nextInt();
            int v = s.nextInt();
            int weight = 1;
            if (isWeighted) {
                weight = s.nextInt();
            }
            graph.addEdge(u, v, weight);
        }
        graph.printAdjList();
        graph.bfs();
        graph.dfs();
        graph.detectCycle();
        System.out.println("Topological Sort :-");
        graph.topologicalSort();
        System.out.print("Enter Source :- ");
        int source = s.nextInt();
        graph.shortestPath(source);
        System.out.println("Prim's MST :-");
        System.out.println(graph.primsMST());
        System.out.println("Kruskal's MST :-");
        System.out.println(graph.kruskalMST());
        System.out.println("Bridges :-\n" + graph.findBridges());
        System.out.println("Articulation Points :-\n" + graph.articulationPoints());
        System.out.println("Strongly Connected Components :-\n" + graph.stronglyConnectedComponents());
        s.close();
    }
}
