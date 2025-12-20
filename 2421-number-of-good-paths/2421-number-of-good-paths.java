import java.util.*;

class Solution {

    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        int n = vals.length;
        
        // adjacency list (only store directed edges toward lower or equal val)
        ArrayList<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();

        // group nodes by their values (sorted because TreeMap)
        TreeMap<Integer, ArrayList<Integer>> sameValues = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            sameValues.computeIfAbsent(vals[i], z -> new ArrayList<>()).add(i);
        }

        // build directed adjacency: always point from higher val to lower val
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            if (vals[u] >= vals[v]) adj[u].add(v);
            else adj[v].add(u);
        }

        // DSU
        UF uf = new UF(n);
        int ans = 0;

        // Process values in increasing order
        for (int value : sameValues.keySet()) {

            // union nodes whose neighbor has val <= current value
            for (int u : sameValues.get(value)) {
                for (int v : adj[u]) {
                    uf.union(u, v);
                }
            }

            // Count how many nodes of this value belong to each component
            HashMap<Integer, Integer> group = new HashMap<>();
            for (int u : sameValues.get(value)) {
                int root = uf.find(u);
                group.put(root, group.getOrDefault(root, 0) + 1);
            }

            // Each node individually is a good path (length 0)
            ans += sameValues.get(value).size();

            // Count combinations of nodes in the same group
            for (int size : group.values()) {
                ans += size * (size - 1) / 2; // choose 2
            }
        }

        return ans;
    }

    // Disjoint Set Union with size tracking
    class UF {
        int[] parent;

        UF(int n) {
            parent = new int[n];
            Arrays.fill(parent, -1); // negative means size
        }

        int find(int x) {
            if (parent[x] < 0) return x;
            return parent[x] = find(parent[x]);
        }

        boolean union(int a, int b) {
            a = find(a);
            b = find(b);
            if (a == b) return false;

            // union by size (parent[] stores negative sizes)
            if (parent[a] > parent[b]) {
                int temp = a; a = b; b = temp;
            }
            parent[a] += parent[b];
            parent[b] = a;
            return true;
        }
    }
}
