import java.util.*;

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        // build adjacency list: node 1..n
        List<int[]>[] adj = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) adj[i] = new ArrayList<>();
        for (int[] t : times) {
            int u = t[0], v = t[1], w = t[2];
            adj[u].add(new int[] { v, w });
        }

        final int INF = Integer.MAX_VALUE / 4;
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[k] = 0;

        // min-heap of (distance, node)
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.add(new int[] { 0, k });

        boolean[] seen = new boolean[n + 1];

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int d = cur[0], u = cur[1];
            if (seen[u]) continue;    // if we already processed u with shortest distance
            seen[u] = true;

            for (int[] e : adj[u]) {
                int v = e[0], w = e[1];
                if (dist[v] > d + w) {
                    dist[v] = d + w;
                    pq.add(new int[] { dist[v], v });
                }
            }
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == INF) return -1;
            ans = Math.max(ans, dist[i]);
        }
        return ans;
    }
}
