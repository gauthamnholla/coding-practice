import java.util.*;

class Solution {
    private static final long INF = Long.MAX_VALUE / 4;

    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {
        // build adjacency list for original graph and reversed graph
        List<int[]>[] graph = new ArrayList[n];
        List<int[]>[] rev = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            rev[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            graph[u].add(new int[] { v, w });
            rev[v].add(new int[] { u, w }); // reversed edge for Dijkstra from dest
        }

        long[] dist1 = dijkstra(n, graph, src1);
        long[] dist2 = dijkstra(n, graph, src2);
        long[] distToDest = dijkstra(n, rev, dest); // distances in reversed graph

        long ans = INF;
        for (int m = 0; m < n; m++) {
            if (dist1[m] == INF || dist2[m] == INF || distToDest[m] == INF) continue;
            ans = Math.min(ans, dist1[m] + dist2[m] + distToDest[m]);
        }
        return ans == INF ? -1 : ans;
    }

    // standard Dijkstra returning distance array of length n
    private long[] dijkstra(int n, List<int[]>[] adj, int src) {
        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        dist[src] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        pq.add(new long[] { 0L, src });

        boolean[] seen = new boolean[n];

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long d = cur[0];
            int u = (int) cur[1];
            if (seen[u]) continue;
            seen[u] = true;

            for (int[] e : adj[u]) {
                int v = e[0];
                int w = e[1];
                if (dist[v] > d + w) {
                    dist[v] = d + w;
                    pq.add(new long[] { dist[v], v });
                }
            }
        }
        return dist;
    }
}
