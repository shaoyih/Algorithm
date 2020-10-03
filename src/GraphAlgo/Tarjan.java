package GraphAlgo;

import GraphAlgo.Utils.GraphGenerator;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Tarjan {

    private boolean[] inStack;
    private int[] lowLink;
    private boolean[] visited;
    private Stack<Integer> stack;

    private Map<Integer, List<Integer>> solve(int n, Map<Integer, List<Integer>> graph) {
        inStack = new boolean[n];
        visited = new boolean[n];
        lowLink = new int[n];
        Arrays.fill(lowLink, -1);
        stack = new Stack<>();
        for(int i = 0; i < n; i++)
            dfs(graph, i);
        Map<Integer, List<Integer>> res = new HashMap<>();
        for(int i = 0; i < n; i++) {
            res.putIfAbsent(lowLink[i], new ArrayList<>());
            res.get(lowLink[i]).add(i);
        }
        for(int i : lowLink) System.out.print(i + " ");
        System.out.println();
        return res;
    }

    private void dfs(Map<Integer, List<Integer>> graph, int cur) {
        if(visited[cur])return;
        if(inStack[cur]) {
            while(!stack.isEmpty() && stack.peek() != cur) {
                int t = stack.pop();
                lowLink[t] = cur;
                //inStack[t] = false;
            }
            return;
        }
        lowLink[cur] = cur;
        stack.push(cur);
        inStack[cur] = true;
        List<Integer> neighbours = graph.get(cur);
        for(int nei : neighbours) {
            dfs(graph, nei);
        }
        if(!stack.isEmpty()) {
            stack.pop();
        }
        visited[cur] = true;
        inStack[cur] = false;
    }

    public static void main(String[] args) {

//        test1();
        test2();

    }
    private static void test2() {
        GraphGenerator graphGenerator = new GraphGenerator(6);
        graphGenerator.addEdge(0, 1);
        graphGenerator.addEdge(1, 2);
        graphGenerator.addEdge(2, 0);
        graphGenerator.addEdge(1, 5);
        graphGenerator.addEdge(2, 5);
        graphGenerator.addEdge(5, 4);
        graphGenerator.addEdge(4, 5);
        graphGenerator.addEdge(1, 3);
        graphGenerator.addEdge(3, 4);

        Map<Integer, List<Integer>> graph = graphGenerator.getAdjList();
        System.out.println(graph);
        Tarjan tarjan = new Tarjan();
        Map<Integer, List<Integer>> result = tarjan.solve(6, graph);
        System.out.println(result);
    }
    private static void test1() {
        GraphGenerator graphGenerator = new GraphGenerator(9);
        graphGenerator.addEdge(0, 1);
        graphGenerator.addEdge(1, 0);
        graphGenerator.addEdge(0, 2);
        graphGenerator.addEdge(2, 0);
        graphGenerator.addEdge(2, 3);
        graphGenerator.addEdge(1, 3);
        graphGenerator.addEdge(3, 4);
        graphGenerator.addEdge(4, 3);
        graphGenerator.addEdge(5, 1);
        graphGenerator.addEdge(5, 4);
        graphGenerator.addEdge(5, 6);
        graphGenerator.addEdge(6, 4);
        graphGenerator.addEdge(6, 7);
        graphGenerator.addEdge(7, 5);
        graphGenerator.addEdge(8, 8);
        graphGenerator.addEdge(8, 7);
        graphGenerator.addEdge(8, 6);

        Map<Integer, List<Integer>> graph = graphGenerator.getAdjList();
        System.out.println(graph);
        Tarjan tarjan = new Tarjan();
        Map<Integer, List<Integer>> result = tarjan.solve(9, graph);
        System.out.println(result);
    }
}
