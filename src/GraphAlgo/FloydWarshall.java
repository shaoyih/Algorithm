package GraphAlgo;

import GraphAlgo.Utils.GraphGenerator;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.NEGATIVE_INFINITY;
import static java.lang.Double.POSITIVE_INFINITY;


//All pair shortest path
//DP
//time: O(V^3)
public class FloydWarshall {

    double[][] graph;
    int n;
    double[][] dp;
    int[][] next;
    public FloydWarshall(double[][] graph) {
        this.graph = graph;
        n = graph.length;
        dp = new double[n][n];
        next = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] != POSITIVE_INFINITY) next[i][j] = j;
                dp[i][j] = graph[i][j];
            }
        }

    }

    public List<Integer> reconstructShortestPath(int start, int end) {
        solve();
        List<Integer> path = new ArrayList<>();
        if (dp[start][end] == POSITIVE_INFINITY) return path;

        for (int at = start; at != end; at = next[at][end]) {
            //if (at == REACHES_NEGATIVE_CYCLE) return null;
            path.add(at);
        }
        // Return null since there are an infinite number of shortest paths.
        //if (next[at][end] == REACHES_NEGATIVE_CYCLE) return null;
        path.add(end);
        return path;
    }

    private double[][] solve() {
        for(int j = 0; j < n; j++){
            for(int i = 0; i < n; i++) {
                for(int k = 0; k < n; k++) {
                    if(dp[i][j] + dp[j][k] < dp[i][k]) {
                        dp[i][k] = dp[i][j] + dp[j][k];
                        next[i][k] = next[i][j];
                    }
                }
            }
        }


        for(int j = 0; j < n; j++){
            for(int i = 0; i < n; i++) {
                for(int k = 0; k < n; k++) {
                    if(dp[i][j] + dp[j][k] < dp[i][k]) {
                        System.out.println("negative cycle");
                        dp[i][k] = NEGATIVE_INFINITY;
                    }
                }
            }
        }
        return dp;

    }


    public static void main(String[] args) {
        GraphGenerator graphGenerator = new GraphGenerator(7);
        graphGenerator.addEdge(0, 1, 2);
        graphGenerator.addEdge(0, 2, 5);
        graphGenerator.addEdge(0, 6, 10);
        graphGenerator.addEdge(1, 2, 2);
        graphGenerator.addEdge(1, 4, 11);
        graphGenerator.addEdge(2, 6, 2);
        graphGenerator.addEdge(6, 5, 11);
        graphGenerator.addEdge(4, 5, 1);
        graphGenerator.addEdge(5, 4, -2);
        double[][] graph = graphGenerator.getAdjMatrix();

        System.out.println(POSITIVE_INFINITY );
        FloydWarshall floydWarshallSolver = new FloydWarshall(graph);
        double[][] dist = floydWarshallSolver.solve();
        //graphGenerator.printGraph(graph);
        for (int i = 0; i < 7; i++)
            for (int j = 0; j < 7; j++)
                System.out.printf("This shortest path from node %d to node %d is %.3f\n", i, j, dist[i][j]);
    }
}
