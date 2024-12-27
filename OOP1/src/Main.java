



public class Main {
    public static void main(String[] args) {
        Graph graph = Graph.generateRandomGraph(5, 7, true);
        graph.printGraph();

        System.out.println("Breadth-First Search starting from vertex 0:");
        graph.bfs(0);

        System.out.println("Depth-First Search starting from vertex 0:");
        graph.dfs(0);

        System.out.println("Does the graph have a cycle? " + graph.hasCycle());
    }
}
