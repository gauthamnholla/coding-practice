import java.util.*;

class Solution {
    public int maximumInvitations(int[] favorite) {
        int n = favorite.length;

        // indegree of each node in the directed graph (u -> favorite[u])
        int[] indeg = new int[n];
        for (int u = 0; u < n; u++) indeg[favorite[u]]++;

        // depth[u] = longest chain length ending at u coming from nodes removed by Kahn
        int[] depth = new int[n];

        // remove nodes with indegree 0 iteratively (these are not in cycles)
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) if (indeg[i] == 0) q.offer(i);

        while (!q.isEmpty()) {
            int u = q.poll();
            int v = favorite[u];
            // chain to v can be extended by u
            depth[v] = Math.max(depth[v], depth[u] + 1);
            indeg[v]--;
            if (indeg[v] == 0) q.offer(v);
        }

        // Now nodes with indeg > 0 are in cycles.
        boolean[] seen = new boolean[n];
        int maxCycle = 0;         // longest cycle length >= 3
        int pairSum = 0;          // sum over all 2-cycles (2 + depth[a] + depth[b])

        for (int i = 0; i < n; i++) {
            if (indeg[i] > 0 && !seen[i]) {
                // traverse the cycle starting at i
                int curr = i;
                int len = 0;
                List<Integer> nodes = new ArrayList<>();
                while (!seen[curr]) {
                    seen[curr] = true;
                    nodes.add(curr);
                    curr = favorite[curr];
                    len++;
                }

                if (len == 2) {
                    // mutual pair a <-> b
                    int a = nodes.get(0), b = nodes.get(1);
                    pairSum += 2 + depth[a] + depth[b];
                } else {
                    // longer cycle contributes its length (we can seat entire cycle)
                    maxCycle = Math.max(maxCycle, len);
                }
            }
        }

        return Math.max(maxCycle, pairSum);
    }
}
