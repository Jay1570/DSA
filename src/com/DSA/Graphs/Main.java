package com.DSA.Graphs;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>();
        Scanner s = new Scanner(System.in);
        System.out.print("Enter number of Edges :- ");
        int edges = s.nextInt();
        System.out.println();
        for (int i = 0; i < edges; i++) {
            int u = s.nextInt();
            int v = s.nextInt();
            graph.addEdge(u, v, false);
        }
        graph.printAdjList();
    }
}
