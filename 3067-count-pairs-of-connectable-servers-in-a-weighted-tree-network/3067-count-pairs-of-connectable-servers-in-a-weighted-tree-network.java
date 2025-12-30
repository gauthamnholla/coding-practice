import java.util.*;

class Solution {
    List<int[]>[] adj;
    int mod;

    int dfs(int parent, int node, int sum) {
        int ans = 0;
        if (sum % mod == 0) ans++;

        for (int[] nei : adj[node]) {
            if (nei[0] == parent) continue;
            ans += dfs(node, nei[0], sum + nei[1]);
        }
        return ans;
    }

    int calc(int node) {
        int prev = 0, res = 0;
        for (int[] nei : adj[node]) {
            int k = dfs(node, nei[0], nei[1]);
            res += prev * k;
            prev += k;
        }
        return res;
    }

    public int[] countPairsOfConnectableServers(int[][] edges, int k) {
        int n = edges.length + 1;
        mod = k;

        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();

        // build graph
        for (int[] e : edges) {
            adj[e[0]].add(new int[]{e[1], e[2]});
            adj[e[1]].add(new int[]{e[0], e[2]});
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = calc(i);
        }
        return ans;
    }
}
