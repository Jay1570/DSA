package com.DSA.Graphs;

import java.util.*;

public class Graph<T> {

    private final HashMap<T, Node<T>> nodes;
    private final boolean isDirected;

    public Graph(boolean isDirected) {
        nodes = new HashMap<>();
        this.isDirected = isDirected;
    }

    private Node<T> getOrCreateNode(T value) {
        if (!nodes.containsKey(value)) {
            nodes.put(value, new Node<>(value));
        }
        return nodes.get(value);
    }

    public void addEdge(T fromValue, T toValue, int weight) {
        Node<T> fromNode = getOrCreateNode(fromValue);
        Node<T> toNode = getOrCreateNode(toValue);

        fromNode.edges.add(new Edge<>(toNode, weight));
        if (!isDirected) {
            toNode.edges.add(new Edge<>(fromNode, weight));
        }
    }

    public void printAdjList() {
        System.out.println(this);
    }

    public void bfs() {
        HashSet<T> visited = new HashSet<>();
        System.out.print("BFS :- ");
        for (T value : nodes.keySet()) {
            if (!visited.contains(value)) {
                bfs(value, visited);
            }
        }
        System.out.println();
    }

    private void bfs(T value, HashSet<T> visited) {
        Queue<T> queue = new LinkedList<>();
        visited.add(value);
        queue.add(value);

        while (!queue.isEmpty()) {
            T front = queue.poll();
            System.out.print(front + ", ");

            for (Edge<T> edge : nodes.get(front).edges) {
                if (!visited.contains(edge.destination.value)) {
                    queue.add(edge.destination.value);
                    visited.add(edge.destination.value);
                }
            }
        }
    }

    public void dfs() {
        HashSet<T> visited = new HashSet<>();
        System.out.print("DFS :- ");
        for (T value : nodes.keySet()) {
            if (!visited.contains(value)) dfs(value, visited);
        }
    }

    private void dfs(T value, HashSet<T> visited) {
        visited.add(value);
        System.out.print(value + ", ");

        for (Edge<T> edge : nodes.get(value).edges) {
            if (!visited.contains(edge.destination.value)) dfs(edge.destination.value, visited);
        }
    }

    public void detectCycle() {
        if (isDirected) {
            System.out.println("Detect Cycle BFS :- " + detectCycleDirectedBFS());
            System.out.println("Detect Cycle DFS :- " + detectCycleDirectedDFS());
        } else {
            System.out.println("Detect Cycle BFS :- " + detectCycleBFS());
            System.out.println("Detect Cycle DFS :- " + detectCycleDFS());
        }
    }

    private boolean detectCycleBFS() {
        HashSet<T> visited = new HashSet<>();
        HashMap<T, T> parent = new HashMap<>();

        for (T value : nodes.keySet()) {
            if (!visited.contains(value)) {
                if(detectCycleBFS(value, visited, parent)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean detectCycleBFS(T value, HashSet<T> visited, HashMap<T, T> parent) {
        Queue<T> queue = new LinkedList<>();
        queue.add(value);
        visited.add(value);

        while (!queue.isEmpty()) {
            T front = queue.poll();

            for (Edge<T> edge : nodes.get(front).edges) {
                if (!visited.contains(edge.destination.value)) {
                    queue.add(edge.destination.value);
                    visited.add(edge.destination.value);
                    parent.put(edge.destination.value, front);
                } else if (parent.get(front) != edge.destination.value) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean detectCycleDFS() {
        HashSet<T> visited = new HashSet<>();

        for (T value : nodes.keySet()) {
            if (!visited.contains(value)) {
                if(detectCycleDFS(value, visited, null)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean detectCycleDFS(T value, HashSet<T> visited, T parent) {
        visited.add(value);

        for (Edge<T> edge : nodes.get(value).edges) {
            if (!visited.contains(edge.destination.value)) {
                if (detectCycleDFS(edge.destination.value, visited, value)) {
                    return true;
                }
            } else if (!edge.destination.value.equals(parent)) {
                return true;
            }
        }
        return false;
    }

    private boolean detectCycleDirectedDFS() {
        HashSet<T> visited = new HashSet<>();

        for (T value : nodes.keySet()) {
            if (visited.contains(value)) continue;
            if (detectCycleDirectedDFS(value, visited, new HashSet<>())) {
                return true;
            }
        }
        return false;
    }

    private boolean detectCycleDirectedDFS(T value, HashSet<T> visited, HashSet<T> recursionStack) {
        visited.add(value);
        recursionStack.add(value);

        for (Edge<T> edge : nodes.get(value).edges) {
            if (!visited.contains(edge.destination.value)) {
                if (detectCycleDirectedDFS(edge.destination.value, visited, recursionStack)) {
                    return true;
                }
            } else if (recursionStack.contains(edge.destination.value)) {
                return true;
            }
        }
        recursionStack.remove(value);
        return false;
    }

    private boolean detectCycleDirectedBFS() {
        HashMap<T, Integer> inDegree = new HashMap<>();

        for (T value : nodes.keySet()) {
            for (Edge<T> edge : nodes.get(value).edges) {
                inDegree.put(edge.destination.value, inDegree.getOrDefault(edge.destination.value, 0) + 1);
            }
        }
        Queue<T> queue = new LinkedList<>();
        for (T value : nodes.keySet()) {
            if (inDegree.getOrDefault(value, 0) == 0)
                queue.add(value);
        }

        int count = 0;
        while (!queue.isEmpty()) {
            T front = queue.poll();
            count++;
            for (Edge<T> edge : nodes.get(front).edges) {
                inDegree.put(edge.destination.value, inDegree.get(edge.destination.value) - 1);
                if (inDegree.get(edge.destination.value) == 0)
                    queue.add(edge.destination.value);
            }
        }
        return count != nodes.size();
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

        for (T value : nodes.keySet()) {
            if (!visited.contains(value)) {
                topologicalSortDFS(value, visited, s);
            }
        }
        return s;
    }

    private void topologicalSortDFS(T value, HashSet<T> visited, Stack<T> s) {
        visited.add(value);
        for (Edge<T> edge : nodes.get(value).edges) {
            if (!visited.contains(edge.destination.value)) {
                topologicalSortDFS(edge.destination.value, visited, s);
            }
        }
        s.push(value);
    }

    // Kahn's Algorithm (BFS)

    private void topologicalSortBFS() {
        HashMap<T, Integer> inDegree = new HashMap<>();

        for (T value : nodes.keySet()) {
            for (Edge<T> edge : nodes.get(value).edges) {
                inDegree.put(edge.destination.value, inDegree.getOrDefault(edge.destination.value, 0) + 1);
            }
        }
        Queue<T> queue = new LinkedList<>();
        for (T value : nodes.keySet()) {
            if (inDegree.getOrDefault(value, 0) == 0)
                queue.add(value);
        }
        while (!queue.isEmpty()) {
            T front = queue.poll();
            System.out.print(front + ", ");
            for (Edge<T> edge : nodes.get(front).edges) {
                inDegree.put(edge.destination.value, inDegree.get(edge.destination.value) - 1);
                if (inDegree.get(edge.destination.value) == 0)
                    queue.add(edge.destination.value);
            }
        }
    }

    public void shortestPath(T source) {
        if (isDirected) {
            System.out.println("Using Topological Sort :- ");
            shortestPathToAllNodeFromSource(source);
        }
        System.out.println("Dijkstra's Algorithm :- ");
        dijkstra(source);
    }

    private void shortestPathToAllNodeFromSource(T source) {
        if (detectCycleDirectedDFS()) {
            System.out.println("Graph has to be Directed Acyclic Graph");
            return;
        }
        Stack<T> topoSort = topologicalSortDFS();
        HashMap<T, Integer> dist = new HashMap<>();

        for(T value : nodes.keySet()) {
            dist.put(value, Integer.MAX_VALUE);
        }
        dist.put(source, 0);
        while (!topoSort.isEmpty()) {
            T top = topoSort.pop();

            if (dist.get(top) != Integer.MAX_VALUE) {
                for (Edge<T> edge : nodes.get(top).edges) {
                    int newDist = dist.get(top) + edge.weight;
                    if (newDist < dist.get(edge.destination.value)) {
                        dist.put(edge.destination.value, newDist);
                    }
                }
            }
        }
        System.out.println("Shortest Distance is :- ");
        for (T node : dist.keySet()) {
            System.out.println(node + " -> " + dist.get(node));
        }
    }

    //dijkstra's algorithm for shortest path
    private void dijkstra(T source) {
        PriorityQueue<Edge<T>> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        HashMap<T, Integer> dist = new HashMap<>();

        for (T value : nodes.keySet()) {
            dist.put(value, Integer.MAX_VALUE);
        }

        dist.put(source, 0);
        pq.add(new Edge<>(nodes.get(source), 0));

        while (!pq.isEmpty()) {
            Edge<T> currentEdge = pq.poll();

            for (Edge<T> edge : currentEdge.destination.edges) {
                int newDist = dist.get(currentEdge.destination.value) + edge.weight;

                if (newDist < dist.get(edge.destination.value)) {
                    dist.put(edge.destination.value, newDist);
                    pq.offer(new Edge<>(edge.destination, newDist));
                }
            }
        }
        System.out.println("Shortest Paths :- ");
        for (T node : dist.keySet()) {
            System.out.println(node + " -> " + dist.get(node));
        }
    }

    public HashMap<T, List<Edge<T>>> primsMST() {
        HashMap<T, Integer> key = new HashMap<>();
        HashSet<T> mst = new HashSet<>();
        HashMap<T, T> parent = new HashMap<>();

        for (T value : nodes.keySet()) {
            key.put(value, Integer.MAX_VALUE);
            parent.put(value, null);
        }

        key.put(nodes.keySet().iterator().next(), 0);

        for (T i : nodes.keySet()) {
            int min = Integer.MAX_VALUE;
            T u = null;

            for (T v : nodes.keySet()) {
                if (!mst.contains(v) && key.get(v) < min) {
                    u = v;
                    min = key.get(v);
                }
            }

            mst.add(u);

            for (Edge<T> e : nodes.get(u).edges) {
                T v = e.destination.value;
                int w = e.weight;

                if (!mst.contains(v) && w < key.get(v)) {
                    parent.put(v, u);
                    key.put(v, w);
                }
            }
        }

        HashMap<T, List<Edge<T>>> ans = new HashMap<>();
        for (T value : nodes.keySet()) {
            if (parent.get(value) == null) continue;
            ans.putIfAbsent(parent.get(value), new ArrayList<>());
            ans.get(parent.get(value)).add(new Edge<>(nodes.get(value), key.get(value)));
        }
        return ans;
    }

    /*
    * Kruskal's Algorithm for Minimum Spanning Tree
    * It uses Union by Rank and Path Compression method to get MST
    */

    public HashMap<T, List<Edge<T>>> kruskalMST() {
        HashMap<T, T> parents = new HashMap<>();
        HashMap<T, Integer> ranks = new HashMap<>();
        List<Edges<T>> edges = new ArrayList<>();

        for (T value : nodes.keySet()) {
            parents.put(value, value);
            ranks.put(value, 0);
            Node<T> node = nodes.get(value);
            for (Edge<T> edge : node.edges) {
                edges.add(new Edges<>(edge, node.value));
            }
        }

        edges.sort(Comparator.comparingInt(e -> e.weight));
        HashMap<T, List<Edge<T>>> mst = new HashMap<>();
        for (Edges<T> edge : edges) {
            T u = findParent(edge.source, parents);
            T v = findParent(edge.destination, parents);
            int w = edge.weight;

            if (u != v) {
                mst.putIfAbsent(edge.source, new ArrayList<>());
                mst.get(edge.source).add(new Edge<>(nodes.get(edge.destination), w));
                union(u, v, parents, ranks);
            }
        }
        return mst;
    }

    //only for kruskal's algorithm
    private T findParent(T node,HashMap<T, T> parents) {
        T parent = parents.get(node);
        if (parent == node) {
            return parent;
        }
        parents.put(node, findParent(parent, parents));
        return parents.get(node);
    }

    private void union(T root1, T root2, HashMap<T, T> parents, HashMap<T, Integer> ranks) {
        T u = findParent(root1, parents);
        T v = findParent(root2, parents);

        if (ranks.get(u) < ranks.get(v)) {
            parents.put(u, v);
        } else if (ranks.get(v) < ranks.get(u)) {
            parents.put(v, u);
        } else {
            parents.put(v, u);
            ranks.put(u, ranks.get(u) + 1);
        }
    }

    public ArrayList<ArrayList<T>> findBridges() {
        HashMap<T, Integer> disc = new HashMap<>();
        HashMap<T, Integer> low = new HashMap<>();
        HashSet<T> visited = new HashSet<>();
        int timer = 0;

        for (T value : nodes.keySet()) {
            disc.put(value, -1);
            low.put(value, -1);
        }

        ArrayList<ArrayList<T>> ans = new ArrayList<>();

        for (T value : nodes.keySet()) {
            if (!visited.contains(value)) {
                findBridges(value, null, timer, disc, low, visited, ans);
            }
        }

        return ans;
    }

    private void findBridges(T node, T parent, int timer, HashMap<T, Integer> disc, HashMap<T, Integer> low, HashSet<T> visited, ArrayList<ArrayList<T>> ans) {
        visited.add(node);
        disc.put(node, timer);
        low.put(node, timer);
        timer++;

        for (Edge<T> edge : nodes.get(node).edges) {
            if (edge.destination.value == parent) {
                continue;
            }
            if (!visited.contains(edge.destination.value)) {
                findBridges(edge.destination.value, node, timer, disc, low, visited, ans);
                low.put(node, Integer.min(low.get(node), low.get(edge.destination.value)));

                if (low.get(edge.destination.value) > disc.get(node)) {
                    ans.add(new ArrayList<>(Arrays.asList(node, edge.destination.value)));
                }
            } else {
                low.put(node, Integer.min(low.get(node), disc.get(edge.destination.value)));
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Adjacency List :-\n");
        for (T value : nodes.keySet()) {
            sb.append(value);
            sb.append(" :- ");
            Node<T> node = nodes.get(value);
            for (Edge<T> edge : node.edges) {
                sb.append("(");
                sb.append(edge.destination.value);
                sb.append(", weight:");
                sb.append(edge.weight);
                sb.append("), ");
            }
            sb.delete(sb.length() - 2, sb.length() - 1);
            sb.append("\n");
        }
        return sb.toString();
    }

    static class Node<T> {
        T value;
        List<Edge<T>> edges;

        Node(T value) {
            this.value = value;
            this.edges = new ArrayList<>();
        }
    }

    public static class Edge<T> {
        Node<T> destination;
        int weight;

        Edge(Node<T> destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "(" + destination.value + ", weight: " + weight + ")";
        }
    }

    private static class Edges<T> {
        T source;
        T destination;
        int weight;

        Edges(Edge<T> edge, T source) {
            this.source = source;
            this.destination = edge.destination.value;
            this.weight = edge.weight;
        }
    }
}
