package GraphAlgo;

import GraphAlgo.Utils.GraphGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

//single source shortest path
//negative cycle
//traverse v - 1 times for all of edges
//time: O(V * E)
public class BellmanFord {

    private int[] solve(Map<Integer, List<int[]>> graph, int start) {
        int n = graph.size();
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        //start traverse edges -> stable state
        for(int i = 1; i < n; i++) {
            for(int v : graph.keySet()) {
                List<int[]> nvs = graph.get(v);
                for(int[] nv : nvs) {
                    int from = v;
                    int to = nv[0];
                    int val = nv[1];
                    if(dist[from] != Integer.MAX_VALUE && dist[from] + val < dist[to]) {
                        dist[to] = dist[from] + val;

                    }
                }
            }
        }

        //find negative cycle
        for(int i = 1; i < n; i++) {
            for(int v : graph.keySet()) {
                List<int[]> nvs = graph.get(v);
                for(int[] nv : nvs) {
                    int to = nv[0];
                    int val = nv[1];
                    if(dist[v] != Integer.MAX_VALUE && dist[v] + val < dist[to]) {
                        System.out.println("negative cycle found");
                        return dist;
                    }
                }
            }
        }


        //find negative cycles
        return dist;
    }

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        GraphGenerator graphGenerator = new GraphGenerator(7);
        graphGenerator.addEdge(0, 1, 1);
        graphGenerator.addEdge(0, 2, 1);
        graphGenerator.addEdge(2, 1, 1);
        graphGenerator.addEdge(1, 3, 4);
        graphGenerator.addEdge(3, 2, -6);
        graphGenerator.addEdge(3, 4, 1);
        graphGenerator.addEdge(3, 5, 1);
        graphGenerator.addEdge(4, 5, 1);
        graphGenerator.addEdge(4, 6, 1);
        graphGenerator.addEdge(5, 6, 1);

        Map<Integer, List<int[]>> graph = graphGenerator.getWeightedGraph();
        BellmanFord bellmanFord = new BellmanFord();
        int[] res = bellmanFord.solve(graph, 0);

        Arrays.stream(res).forEach(System.out::println);

    }
}
