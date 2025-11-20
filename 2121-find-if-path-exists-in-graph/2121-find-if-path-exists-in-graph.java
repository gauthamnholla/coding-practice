import java.util.*;

class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        if (source == destination) return true;

        // build adjacency list
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            adj[u].add(v);
            adj[v].add(u);
        }

        boolean[] seen = new boolean[n];
        Deque<Integer> q = new ArrayDeque<>();
        q.add(source);
        seen[source] = true;

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : adj[u]) {
                if (!seen[v]) {
                    if (v == destination) return true;
                    seen[v] = true;
                    q.add(v);
                }
            }
        }
        return false;
    }
}
