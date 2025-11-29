import java.util.*;

class Solution {
    static class DSU {
        int[] parent, rank;
        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        int find(int x) {
            return parent[x] == x ? x : (parent[x] = find(parent[x]));
        }
        boolean union(int a, int b) {
            a = find(a);
            b = find(b);
            if (a == b) return false;
            if (rank[a] < rank[b]) parent[a] = b;
            else if (rank[a] > rank[b]) parent[b] = a;
            else { parent[b] = a; rank[a]++; }
            return true;
        }
    }

    // ✔ Judge expected name
    public int maxStability(int n, int[][] edges, int k) {
        int lo = 1, hi = 200000;
        int ans = -1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (can(mid, n, edges, k)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    private boolean can(int X, int n, int[][] edges, int k) {
        DSU dsu = new DSU(n);
        int usedUpgrades = 0;

        List<int[]> freeEdges = new ArrayList<>();
        List<int[]> upgradableEdges = new ArrayList<>();

        // Step 1: mandatory edges
        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3];
            if (must == 1) {
                if (s < X) return false;
                if (!dsu.union(u, v)) return false;
            }
        }

        // Step 2: classify optional edges
        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3];
            if (must == 1) continue;

            if (s >= X) freeEdges.add(e);
            else if (2 * s >= X) upgradableEdges.add(e);
        }

        // Step 3: add free usable edges
        for (int[] e : freeEdges) dsu.union(e[0], e[1]);

        // Step 4: use upgrades only when needed
        for (int[] e : upgradableEdges) {
            if (dsu.union(e[0], e[1])) {
                usedUpgrades++;
                if (usedUpgrades > k) return false;
            }
        }

        // Check full connectivity
        int root = dsu.find(0);
        for (int i = 1; i < n; i++)
            if (dsu.find(i) != root) return false;

        return true;
    }
}
