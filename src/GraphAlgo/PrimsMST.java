package GraphAlgo;

import GraphAlgo.Utils.GraphGenerator;

import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

//used to generate minimum spanning tree
//get MST  from a graph
public class PrimsMST {

    boolean[] visited;
    Map<Integer, List<int[]>> graph;
    int n;
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

    public PrimsMST(Map<Integer, List<int[]>> graph, int n) {
        this.graph = graph;
        this.n = n;
        visited = new boolean[n];
    }

    private void addEdges(int node) {
        List<int[]> neis = graph.get(node);
        pq.addAll(neis);
    }


    private int solve() {
        int res = 0;
        int visitedNodes = 1;
        visited[0] = true;
        addEdges(0);
        while(visitedNodes != n && !pq.isEmpty()) {
            int[] edge = pq.poll();
            if(visited[edge[0]])continue;
            System.out.println(edge[0]);
            addEdges(edge[0]);
            visited[edge[0]] = true;
            res += edge[1];
            visitedNodes++;
        }

        //no MST exist
        if(visitedNodes != n) return -1;

        return res;
    }



    public static void main(String[] args) {
        GraphGenerator graphGenerator = new GraphGenerator(10);
        graphGenerator.addTwoEdge(0, 1, 10);
        graphGenerator.addTwoEdge(0, 2, 1);
        graphGenerator.addTwoEdge(0, 3, 4);
        graphGenerator.addTwoEdge(1, 2, 3);
        graphGenerator.addTwoEdge(1, 4, 0);
        graphGenerator.addTwoEdge(2, 3, 2);
        graphGenerator.addTwoEdge(2, 5, 8);
        graphGenerator.addTwoEdge(3, 5, 2);
        graphGenerator.addTwoEdge(3, 6, 7);
        graphGenerator.addTwoEdge(4, 5, 1);
        graphGenerator.addTwoEdge(4, 7, 8);
        graphGenerator.addTwoEdge(5, 6, 6);
        graphGenerator.addTwoEdge(5, 7, 9);
        graphGenerator.addTwoEdge(6, 7, 12);

        Map<Integer, List<int[]>> graph = graphGenerator.getWeightedGraph();
        PrimsMST primsMSTSolver = new PrimsMST(graph, 8);
        int res = primsMSTSolver.solve();
        System.out.println(res);
    }
}
