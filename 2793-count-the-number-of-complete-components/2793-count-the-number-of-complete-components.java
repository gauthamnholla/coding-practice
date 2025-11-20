import java.util.*;

class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        // build adjacency list
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            adj[u].add(v);
            adj[v].add(u);
        }

        boolean[] seen = new boolean[n];
        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (seen[i]) continue;

            // collect this component via DFS
            List<Integer> comp = new ArrayList<>();
            Deque<Integer> stack = new ArrayDeque<>();
            stack.push(i);
            seen[i] = true;

            while (!stack.isEmpty()) {
                int u = stack.pop();
                comp.add(u);
                for (int v : adj[u]) {
                    if (!seen[v]) {
                        seen[v] = true;
                        stack.push(v);
                    }
                }
            }

            int k = comp.size();
            // count edges internal to this component
            int edgeCount = 0;
            // for each node in component, count neighbors that are also in component
            // to make containment check O(1) we can mark them in a boolean array
            boolean[] inComp = new boolean[n];
            for (int u : comp) inComp[u] = true;

            for (int u : comp) {
                for (int v : adj[u]) {
                    if (inComp[v]) edgeCount++;
                }
            }
            edgeCount /= 2; // every internal edge counted twice

            // a complete graph on k vertices has k*(k-1)/2 edges
            if (edgeCount == k * (k - 1) / 2) ans++;

            // cleanup inComp marks (optional but keeps memory tidy)
            for (int u : comp) inComp[u] = false;
        }

        return ans;
    }
}
