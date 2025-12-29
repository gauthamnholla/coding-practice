import java.util.*;

class Solution {

    private List<Integer>[] graph;

    public long countPairs(int n, int[][] edges) {
        createGraph(n, edges);

        boolean[] visited = new boolean[n];
        long result = 0;
        long visitedCount = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int size = dfs(i, visited);
                result += size * visitedCount;
                visitedCount += size;
            }
        }
        return result;
    }

    private int dfs(int node, boolean[] visited) {
        visited[node] = true;
        int count = 1;

        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                count += dfs(neighbor, visited);
            }
        }
        return count;
    }

    private void createGraph(int n, int[][] edges) {
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
    }
}
