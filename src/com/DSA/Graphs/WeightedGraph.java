package com.DSA.Graphs;

import java.util.*;

public class WeightedGraph<T> {

    private final HashMap<T, List<Edge<T>>> adjList = new HashMap<>();
    private final boolean isDirected;

    public WeightedGraph(boolean isDirected) {
        this.isDirected = isDirected;
    }

    public void addEdge(T u, T v, int weight) {
        adjList.putIfAbsent(u, new ArrayList<>());
        adjList.get(u).add(new Edge<>(v, weight));
        adjList.putIfAbsent(v, new ArrayList<>());
        if (!isDirected) {
            adjList.get(v).add(new Edge<>(u, weight));
        }
    }

    public void printAdjList() {
        System.out.println("Adjacency List :-");
        for (T node : adjList.keySet()) {
            System.out.print(node + " -> ");
            for (Edge<T> edge : adjList.getOrDefault(node, new ArrayList<>())) {
                System.out.print("(" + edge.node + ", weight: " + edge.weight + "), ");
            }
            System.out.println();
        }
    }

    public void bfs() {
        HashSet<T> visited = new HashSet<>();
        System.out.print("BFS :- ");
        for(T key : adjList.keySet()) {
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

            for (Edge<T> neighbours : adjList.getOrDefault(front, new ArrayList<>())) {
                if(!visited.contains(neighbours.node)) {
                    queue.add(neighbours.node);
                    visited.add(neighbours.node);
                }
            }
        }
    }

    public void dfs() {
        HashSet<T> visited = new HashSet<>();
        System.out.print("DFS :- ");
        for (T key: adjList.keySet()) {
            if (!visited.contains(key)) {
                dfs(key, visited);
            }
        }
    }

    private void dfs(T node, HashSet<T> visited) {
        System.out.print(node + ", ");
        visited.add(node);

        for (Edge<T> neighbours : adjList.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbours.node)) dfs(neighbours.node, visited);
        }
    }

    public void detectCycle() {
        if (!isDirected) {
            System.out.println("Detect Cycle BFS :- " + detectCycleBFS());
            System.out.println("Detect Cycle DFS :- " + detectCycleDFS());
        } else {
            System.out.println("Detect Cycle BFS(Kahn's Algo) :- " + detectCycleDirectedBFS());
            System.out.println("Detect Cycle DFS :- " + detectCycleDirectedDFS());
        }
    }

    private boolean detectCycleBFS() {
        HashSet<T> visited = new HashSet<>();
        HashMap<T, T> parent = new HashMap<>();

        for (T key : adjList.keySet()) {
            if (visited.contains(key)) continue;
            if (detectCycleBFS(key, visited, parent)) {
                return true;
            }
        }
        return false;
    }

    private boolean detectCycleBFS(T node, HashSet<T> visited, HashMap<T, T> parent) {
        Queue<T> queue = new LinkedList<>();
        queue.add(node);
        visited.add(node);
        parent.put(node, null);

        while (!queue.isEmpty()) {
            T front = queue.poll();
            for(Edge<T> neighbour : adjList.getOrDefault(front, new ArrayList<>())) {
                if (!visited.contains(neighbour.node)) {
                    queue.add(neighbour.node);
                    visited.add(neighbour.node);
                    parent.put(neighbour.node, front);
                } else if (parent.get(front) != neighbour) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean detectCycleDFS() {
        HashSet<T> visited = new HashSet<>();

        for(T key : adjList.keySet()) {
            if (visited.contains(key)) continue;
            if (detectCycleDFS(key, visited, null)) {
                return true;
            }
        }
        return false;
    }

    private boolean detectCycleDFS(T node, HashSet<T> visited, T parent) {
        visited.add(node);

        for (Edge<T> neighbour : adjList.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbour.node)) {
                if(detectCycleDFS(neighbour.node, visited, node)) {
                    return true;
                }
            } else if (!neighbour.node.equals(parent)) {
                return true;
            }
        }
        return false;
    }

    private boolean detectCycleDirectedDFS() {
        HashSet<T> visited = new HashSet<>();

        for (T key : adjList.keySet()) {
            if (visited.contains(key)) continue;
            if (detectCycleDirectedDFS(key, visited, new HashSet<>())) {
                return true;
            }
        }
        return false;
    }

    private boolean detectCycleDirectedDFS(T node, HashSet<T> visited, HashSet<T> recursionStack) {
        visited.add(node);
        recursionStack.add(node);

        for (Edge<T> neighbour : adjList.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbour.node)) {
                if (detectCycleDirectedDFS(neighbour.node, visited, recursionStack)) {
                    return true;
                }
            } else if (recursionStack.contains(neighbour.node)) {
                return true;
            }
        }
        recursionStack.remove(node);
        return false;
    }

    private boolean detectCycleDirectedBFS() {
        HashMap<T, Integer> inDegree = new HashMap<>();

        for (T node : adjList.keySet()) {
            for (Edge<T> neighbour : adjList.getOrDefault(node, new ArrayList<>())) {
                inDegree.put(neighbour.node, inDegree.getOrDefault(neighbour.node, 0) + 1);
            }
        }
        Queue<T> queue = new LinkedList<>();
        for (T node : adjList.keySet()) {
            if (inDegree.getOrDefault(node, 0) == 0)
                queue.add(node);
        }

        int count = 0;
        while (!queue.isEmpty()) {
            T front = queue.poll();
            count++;
            for (Edge<T> neighbour : adjList.getOrDefault(front, new ArrayList<>())) {
                inDegree.put(neighbour.node, inDegree.get(neighbour.node) - 1);
                if (inDegree.get(neighbour.node) == 0)
                    queue.add(neighbour.node);
            }
        }
        return count != adjList.size();
    }

    /*
     * Topological Sort
     * Can be performed only on Directed Acyclic Graphs
     * It is linear order of vertices such that
     * for every edge u -> v,
     * u always appears before v in that ordering
     */

    public void topologicalSort() {
        if (!isDirected) {
            System.out.println("Graph must be a directed graph");
            return;
        }
        if (detectCycleDirectedDFS()) {
            System.out.println("Graph must be a acyclic graph");
            return;
        }
        System.out.println("Topological Sort :-");
        System.out.print("DFS :- ");
        Stack<T> s = topologicalSortDFS();
        while (!s.isEmpty()) {
            System.out.print(s.pop() + ", ");
        }
        System.out.print("\nKahn's Algorithm(BFS) :- ");
        topologicalSortBFS();
        System.out.println();
    }

    private Stack<T> topologicalSortDFS() {
        HashSet<T> visited = new HashSet<>();
        Stack<T> s = new Stack<>();

        for (T node : adjList.keySet()) {
            if (!visited.contains(node)) {
                topologicalSortDFS(node, visited, s);
            }
        }
        return s;
    }

    private void topologicalSortDFS(T node, HashSet<T> visited, Stack<T> s) {
        visited.add(node);
        for (Edge<T> neighbour : adjList.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbour.node)) {
                topologicalSortDFS(neighbour.node, visited, s);
            }
        }
        s.push(node);
    }

    // Kahn's Algorithm (BFS)

    private void topologicalSortBFS() {
        HashMap<T, Integer> inDegree = new HashMap<>();

        for (T node : adjList.keySet()) {
            for (Edge<T> neighbour : adjList.getOrDefault(node, new ArrayList<>())) {
                inDegree.put(neighbour.node, inDegree.getOrDefault(neighbour.node, 0) + 1);
            }
        }
        Queue<T> queue = new LinkedList<>();
        for (T node : adjList.keySet()) {
            if (inDegree.getOrDefault(node, 0) == 0)
                queue.add(node);
        }
        while (!queue.isEmpty()) {
            T front = queue.poll();
            System.out.print(front + ", ");
            for (Edge<T> neighbour : adjList.getOrDefault(front, new ArrayList<>())) {
                inDegree.put(neighbour.node, inDegree.get(neighbour.node) - 1);
                if (inDegree.get(neighbour.node) == 0)
                    queue.add(neighbour.node);
            }
        }
    }

    public void shortestPathToAllNodeFromSource(T source) {
        Stack<T> topoSort = topologicalSortDFS();
        HashMap<T, Integer> dist = new HashMap<>();

        for(T key : adjList.keySet()) {
            dist.put(key, Integer.MAX_VALUE);
        }
        dist.put(source, 0);
        while (!topoSort.isEmpty()) {
            T top = topoSort.pop();

            if (dist.get(top) != Integer.MAX_VALUE) {
                for (Edge<T> edge : adjList.getOrDefault(top, new ArrayList<>())) {
                    if (dist.get(top) + edge.weight < dist.get(edge.node)) {
                        dist.put(edge.node, dist.get(top) + edge.weight);
                    }
                }
            }
        }
        System.out.println("Shortest Distance is :- ");
        for (T node : dist.keySet()) {
            System.out.println(node + " -> " + dist.get(node));
        }
    }

    static class Edge<T> {
        T node;
        int weight;

        Edge(T node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
}
