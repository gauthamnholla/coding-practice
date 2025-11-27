import java.util.*;

class Solution {
    public int minimumTime(int n, int[][] relations, int[] time) {
        // build graph and indegree
        List<Integer>[] g = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) g[i] = new ArrayList<>();
        int[] indeg = new int[n + 1];
        for (int[] r : relations) {
            int u = r[0], v = r[1];
            g[u].add(v);
            indeg[v]++;
        }

        // earliestFinish[i] = earliest month course i can be finished
        long[] earliestFinish = new long[n + 1];

        // initialize queue with courses that have no prerequisites
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (indeg[i] == 0) {
                earliestFinish[i] = time[i - 1]; // can start at 0
                q.offer(i);
            }
        }

        // process in topological order
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : g[u]) {
                // we can start v only after u finishes; keep the max start time
                earliestFinish[v] = Math.max(earliestFinish[v], earliestFinish[u] + time[v - 1]);
                if (--indeg[v] == 0) q.offer(v);
            }
        }

        // answer is the maximum finish time across all courses
        long ans = 0;
        for (int i = 1; i <= n; i++) ans = Math.max(ans, earliestFinish[i]);
        return (int) ans;
    }
}
