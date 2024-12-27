import java.util.*;

class Graph {
    private final int vertices;
    private final boolean isDirected;
    private final Map<Integer, List<Integer>> adjacencyList;

    public Graph(int vertices, boolean isDirected) {
        this.vertices = vertices;
        this.isDirected = isDirected;
        this.adjacencyList = new HashMap<>();
        for (int i = 0; i < vertices; i++) {
            adjacencyList.put(i, new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination) {
        adjacencyList.get(source).add(destination);
        if (!isDirected) {
            adjacencyList.get(destination).add(source);
        }
    }

    public List<Integer> getNeighbors(int vertex) {
        return adjacencyList.get(vertex);
    }

    public void printGraph() {
        for (int vertex : adjacencyList.keySet()) {
            System.out.println(vertex + " -> " + adjacencyList.get(vertex));
        }
    }

    public static Graph generateRandomGraph(int vertices, int edges, boolean isDirected) {
        Graph graph = new Graph(vertices, isDirected);
        Random random = new Random();
        for (int i = 0; i < edges; i++) {
            int source = random.nextInt(vertices);
            int destination = random.nextInt(vertices);
            graph.addEdge(source, destination);
        }
        return graph;
    }

    public void bfs(int startVertex) {
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startVertex);
        visited[startVertex] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");
            for (int neighbor : adjacencyList.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    public void dfs(int startVertex) {
        boolean[] visited = new boolean[vertices];
        dfsHelper(startVertex, visited);
        System.out.println();
    }

    private void dfsHelper(int vertex, boolean[] visited) {
        visited[vertex] = true;
        System.out.print(vertex + " ");

        for (int neighbor : adjacencyList.get(vertex)) {
            if (!visited[neighbor]) {
                dfsHelper(neighbor, visited);
            }
        }
    }

    public boolean hasCycle() {
        boolean[] visited = new boolean[vertices];
        boolean[] recursionStack = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            if (!visited[i] && hasCycleHelper(i, visited, recursionStack)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasCycleHelper(int vertex, boolean[] visited, boolean[] recursionStack) {
        visited[vertex] = true;
        recursionStack[vertex] = true;

        for (int neighbor : adjacencyList.get(vertex)) {
            if (!visited[neighbor] && hasCycleHelper(neighbor, visited, recursionStack)) {
                return true;
            } else if (recursionStack[neighbor]) {
                return true;
            }
        }

        recursionStack[vertex] = false;
        return false;
    }
}