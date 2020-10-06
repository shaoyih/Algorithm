package GraphAlgo.Utils;

import java.util.*;

import static java.lang.Double.POSITIVE_INFINITY;

public class GraphGenerator {
    List<int[]> edges;
    int n;
    public GraphGenerator() {
        edges = new ArrayList<>();
    }

    public GraphGenerator(int n) {
        this.n = n;
        edges = new ArrayList<>();
    }

    public void addEdge(int x, int y) {
        edges.add(new int[]{x, y});
    }
    public void addTwoEdge(int x, int y) {
        edges.add(new int[]{x, y});
        edges.add(new int[]{y, x});
    }

    public void addEdge(int x, int y, int val) {
        edges.add(new int[]{x, y, val});
    }

    public void addTwoEdge(int x, int y, int val) {
        edges.add(new int[]{x, y, val});
        edges.add(new int[]{y, x, val});
    }

    public void addEdges(List<int[]> edges) {
        this.edges = edges;
    }

    public void clearGraph() {
        edges = new ArrayList<>();
    }

    public void resetN(int n) {
        this.n = n;
    }

    public Map<Integer, List<Integer>> getAdjList() {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, List<int[]>> weightedGraph = new HashMap<>();
        for(int [] e : edges) {
            graph.putIfAbsent(e[0], new ArrayList<>());
            graph.get(e[0]).add(e[1]);
        }
        return  graph;
    }

    public Map<Integer, List<int[]>> getWeightedGraph() {
        Map<Integer, List<int[]>> weightedGraph = new HashMap<>();
        for(int [] e : edges) {
            weightedGraph.putIfAbsent(e[0], new ArrayList<>());
            weightedGraph.get(e[0]).add(new int[]{e[1], e[2]});
        }
        return weightedGraph;

    }

    public double[][] getAdjMatrix() {
        double[][] graph = new double[n][n];
        for(int i = 0; i < n; i++) {
            Arrays.fill(graph[i], POSITIVE_INFINITY);
            graph[i][i] = 0;
        }
        for(int [] e : edges) {
            graph[e[0]][e[1]] = e.length > 2 ? e[2] : 1;
        }
        return graph;
    }

    public void printGraph(double[][] graph) {
        for(int i = 0; i < graph.length; i++) {
            for(int j = 0; j < graph.length; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
    }

}
