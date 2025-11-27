import java.util.*;

class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        int m = edges.length;
        // build graph + indegree
        List<Integer>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        int[] indeg = new int[n];
        for (int[] e : edges) {
            g[e[0]].add(e[1]);
            indeg[e[1]]++;
        }

        // dp[i][c] = max count of letter c on any path ending at node i
        int[][] dp = new int[n][26];
        for (int i = 0; i < n; i++) dp[i][colors.charAt(i) - 'a'] = 1;

        // queue of nodes with indegree 0
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) if (indeg[i] == 0) q.offer(i);

        int seen = 0;
        int ans = 0;

        while (!q.isEmpty()) {
            int u = q.poll();
            seen++;
            // update global max using dp[u]
            for (int c = 0; c < 26; c++) ans = Math.max(ans, dp[u][c]);

            for (int v : g[u]) {
                // propagate counts from u to v
                for (int c = 0; c < 26; c++) {
                    // if dp[u][c] is zero it won't affect dp[v][c]
                    int add = dp[u][c];
                    if (add == 0) continue;
                    // if v has color c, add 1 (but dp[v][c] was already initialized with v's color)
                    int candidate = add + (colors.charAt(v) - 'a' == c ? 1 : 0);
                    if (candidate > dp[v][c]) dp[v][c] = candidate;
                }
                // remove edge u->v
                if (--indeg[v] == 0) q.offer(v);
            }
        }

        // if not all nodes were processed, there is a cycle
        if (seen != n) return -1;
        return ans;
    }
}
