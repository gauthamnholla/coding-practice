import java.util.*;

class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        final int INF = 1_000_000_000;
        int[][] dist = new int[n][n];

        // initialize distances
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        // set direct edges (bidirectional)
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            dist[u][v] = Math.min(dist[u][v], w);
            dist[v][u] = Math.min(dist[v][u], w);
        }

        // Floyd-Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                if (dist[i][k] == INF) continue;
                for (int j = 0; j < n; j++) {
                    if (dist[k][j] == INF) continue;
                    int nd = dist[i][k] + dist[k][j];
                    if (nd < dist[i][j]) dist[i][j] = nd;
                }
            }
        }

        int bestCity = -1;
        int bestCount = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (dist[i][j] <= distanceThreshold) cnt++;
            }
            // prefer smaller count, and on tie prefer larger index
            if (cnt < bestCount || (cnt == bestCount && i > bestCity)) {
                bestCount = cnt;
                bestCity = i;
            }
        }

        return bestCity;
    }
}
