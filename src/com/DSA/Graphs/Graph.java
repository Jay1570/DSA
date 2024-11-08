package com.DSA.Graphs;

import java.util.*;

class Graph<T> {

    private final HashMap<T, List<T>> adjList = new HashMap<>();
    private final boolean isDirected;

    public Graph(boolean isDirected) {
        this.isDirected = isDirected;
    }

    public void addEdge(T u, T v) {
        adjList.putIfAbsent(u, new ArrayList<>());
        adjList.get(u).add(v);

        if (!isDirected) {
            adjList.putIfAbsent(v, new ArrayList<>());
            adjList.get(v).add(u);
        }
    }

    public void printAdjList() {
        System.out.println("Adjacency List :-");
        for (T key : adjList.keySet()) {
            System.out.print(key + " -> ");
            for (T edge : adjList.get(key)) {
                System.out.print(edge + ", ");
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

            for (T neighbours : adjList.getOrDefault(front, new ArrayList<>())) {
                if(!visited.contains(neighbours)) {
                    queue.add(neighbours);
                    visited.add(neighbours);
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

        for (T neighbours : adjList.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbours)) dfs(neighbours, visited);
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
            for(T neighbour : adjList.getOrDefault(front, new ArrayList<>())) {
                if (!visited.contains(neighbour)) {
                    queue.add(neighbour);
                    visited.add(neighbour);
                    parent.put(neighbour, front);
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

        for (T neighbour : adjList.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbour)) {
                if(detectCycleDFS(neighbour, visited, node)) {
                    return true;
                }
            } else if (!neighbour.equals(parent)) {
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

        for (T neighbour : adjList.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbour)) {
                if (detectCycleDirectedDFS(neighbour, visited, recursionStack)) {
                    return true;
                }
            } else if (recursionStack.contains(neighbour)) {
                return true;
            }
        }
        recursionStack.remove(node);
        return false;
    }

    private boolean detectCycleDirectedBFS() {
        HashMap<T, Integer> inDegree = new HashMap<>();

        for (T node : adjList.keySet()) {
            for (T neighbour : adjList.getOrDefault(node, new ArrayList<>())) {
                inDegree.put(neighbour, inDegree.getOrDefault(neighbour, 0) + 1);
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
            for (T neighbour : adjList.getOrDefault(front, new ArrayList<>())) {
                inDegree.put(neighbour, inDegree.get(neighbour) - 1);
                if (inDegree.get(neighbour) == 0)
                    queue.add(neighbour);
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
        topologicalSortDFS();
        System.out.print("\nKahn's Algorithm(BFS) :- ");
        topologicalSortBFS();
        System.out.println();
    }

    private void topologicalSortDFS() {
        HashSet<T> visited = new HashSet<>();
        Stack<T> s = new Stack<>();

        for (T node : adjList.keySet()) {
            if (!visited.contains(node)) {
                topologicalSortDFS(node, visited, s);
            }
        }
        while (!s.isEmpty()) {
            System.out.print(s.pop() + ", ");
        }
    }

    private void topologicalSortDFS(T node, HashSet<T> visited, Stack<T> s) {
        visited.add(node);
        for (T neighbour : adjList.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbour)) {
                topologicalSortDFS(neighbour, visited, s);
            }
        }
        s.push(node);
    }

    // Kahn's Algorithm (BFS)

    private void topologicalSortBFS() {
        HashMap<T, Integer> inDegree = new HashMap<>();

        for (T node : adjList.keySet()) {
            for (T neighbour : adjList.getOrDefault(node, new ArrayList<>())) {
                inDegree.put(neighbour, inDegree.getOrDefault(neighbour, 0) + 1);
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
            for (T neighbour : adjList.getOrDefault(front, new ArrayList<>())) {
                inDegree.put(neighbour, inDegree.get(neighbour) - 1);
                if (inDegree.get(neighbour) == 0)
                    queue.add(neighbour);
            }
        }
    }

    public void shortestPath(T source, T destination) {
        if (!adjList.containsKey(source)) {
            System.out.println("Source Node does not exists...");
            return;
        }
        if (!adjList.containsKey(destination)) {
            System.out.println("Destination Node does not exists...");
            return;
        }
        if (!isDirected) {
            System.out.println("Shortest path between " + source + " and " + destination + " :- " + shortestPathUndirected(source, destination));
        }
    }

    private LinkedList<T> shortestPathUndirected(T source, T destination) {
        Queue<T> queue = new LinkedList<>();
        HashSet<T> visited = new HashSet<>();
        HashMap<T, T> parent = new HashMap<>();
        queue.add(source);
        visited.add(source);
        parent.put(source, null);
        while (!queue.isEmpty()) {
            T front = queue.poll();
            for (T neighbour : adjList.getOrDefault(front, new ArrayList<>())) {
                if (!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    parent.put(neighbour, front);
                    queue.add(neighbour);
                }
            }
        }
        T p = destination;
        LinkedList<T> ans = new LinkedList<>();
        while (p != null) {
            ans.addFirst(p);
            p = parent.get(p);
        }
        return ans;
    }
}