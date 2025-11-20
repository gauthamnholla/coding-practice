import java.util.*;

class Solution {
    private long ans = 0;
    private long[] values;
    private List<int[]>[] adj;

    public int maximalPathQuality(int[] valuesArr, int[][] edges, int maxTime) {
        int n = valuesArr.length;
        values = new long[n];

        for (int i = 0; i < n; i++) values[i] = valuesArr[i];

        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();

        for (int[] e : edges) {
            int u = e[0], v = e[1], t = e[2];
            adj[u].add(new int[]{v, t});
            adj[v].add(new int[]{u, t});
        }

        boolean[] visited = new boolean[n];
        visited[0] = true;
        ans = values[0];

        dfs(0, maxTime, values[0], visited);

        return (int) ans;
    }

    private void dfs(int node, int timeLeft, long curQuality, boolean[] visited) {

        if (node == 0) ans = Math.max(ans, curQuality);

        for (int[] nb : adj[node]) {
            int nxt = nb[0];
            int cost = nb[1];

            if (cost > timeLeft) continue;

            if (!visited[nxt]) {
                visited[nxt] = true;
                dfs(nxt, timeLeft - cost, curQuality + values[nxt], visited);
                visited[nxt] = false;
            } else {
                dfs(nxt, timeLeft - cost, curQuality, visited);
            }
        }
    }
}
