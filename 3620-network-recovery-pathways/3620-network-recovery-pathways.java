import java.util.*;

public class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;

        // Build graph
        ArrayList<int[]>[] g = new ArrayList[n];
        int[] indeg = new int[n];
        int maxCost = 0;
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for (int[] e : edges) {
            g[e[0]].add(new int[]{e[1], e[2]});
            indeg[e[1]]++;
            maxCost = Math.max(maxCost, e[2]);
        }

        // Topo order
        int[] topo = new int[n];
        int t = 0;
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < n; i++) if (indeg[i] == 0) dq.add(i);
        while (!dq.isEmpty()) {
            int u = dq.poll();
            topo[t++] = u;
            for (int[] ed : g[u]) if (--indeg[ed[0]] == 0) dq.add(ed[0]);
        }

        // Binary search on path score (minimum edge)
        int low = 0, high = maxCost, ans = -1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (ok(mid, g, topo, t, online, k, n)) {
                ans = mid;
                low = mid + 1;
            } else high = mid - 1;
        }
        return ans;
    }

    // Feasibility check for threshold
    private boolean ok(int thr, ArrayList<int[]>[] g, int[] topo, int t,
                       boolean[] online, long k, int n) {

        long INF = Long.MAX_VALUE / 4;
        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        dist[0] = 0;

        for (int i = 0; i < t; i++) {
            int u = topo[i];
            if (dist[u] == INF) continue;
            if (!online[u] && u != 0 && u != n - 1) continue;

            for (int[] ed : g[u]) {
                int v = ed[0], c = ed[1];
                if (c < thr) continue;
                if (!online[v] && v != n - 1) continue;

                long nd = dist[u] + c;
                if (nd < dist[v]) dist[v] = nd;
            }
        }
        return dist[n - 1] <= k;
    }
}
