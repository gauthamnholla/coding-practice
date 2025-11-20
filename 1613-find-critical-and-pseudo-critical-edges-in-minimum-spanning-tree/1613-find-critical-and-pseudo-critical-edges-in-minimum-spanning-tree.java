import java.util.*;

class Solution {
    // Union-Find (Disjoint Set Union)
    private static class DSU {
        int[] parent;
        int[] rank;
        public DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        public int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }
        public boolean union(int a, int b) {
            int ra = find(a), rb = find(b);
            if (ra == rb) return false;
            if (rank[ra] < rank[rb]) parent[ra] = rb;
            else if (rank[rb] < rank[ra]) parent[rb] = ra;
            else { parent[rb] = ra; rank[ra]++; }
            return true;
        }
    }

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int m = edges.length;
        // Augment edges with original index
        int[][] ed = new int[m][4];
        for (int i = 0; i < m; i++) {
            ed[i][0] = edges[i][0];
            ed[i][1] = edges[i][1];
            ed[i][2] = edges[i][2];
            ed[i][3] = i; // original index
        }

        // sort edges by weight (for Kruskal)
        Arrays.sort(ed, Comparator.comparingInt(a -> a[2]));

        // function to compute MST weight, optionally skipping one edge or forcing one edge
        // skipIndex, forceIndex refer to original indices (or -1 if none)
        final long INF = Long.MAX_VALUE / 4;
        java.util.function.BiFunction<Integer,Integer,Long> kruskal = (skipIdx, forceIdx) -> {
            DSU dsu = new DSU(n);
            long total = 0;
            int usedEdges = 0;

            // if forceIndex != -1, include that edge first
            if (forceIdx != -1) {
                // find that edge's info in ed[] using original index
                for (int[] e : ed) {
                    if (e[3] == forceIdx) {
                        if (dsu.union(e[0], e[1])) {
                            total += e[2];
                            usedEdges++;
                        }
                        break;
                    }
                }
            }

            for (int[] e : ed) {
                int u = e[0], v = e[1], w = e[2], idx = e[3];
                if (idx == skipIdx) continue;
                // If we already forced include this edge, it's fine; union will simply skip if same component
                if (dsu.union(u, v)) {
                    total += w;
                    usedEdges++;
                }
                if (usedEdges == n - 1) break;
            }

            if (usedEdges == n - 1) return total;
            else return INF; // couldn't form MST (graph not connected with skip)
        };

        // original MST weight
        long original = kruskal.apply(-1, -1);

        List<Integer> critical = new ArrayList<>();
        List<Integer> pseudo = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int edgeIndex = i; // original index is i since edges array given in original order

            // 1) Check critical: skip this edge
            long wSkip = kruskal.apply(edgeIndex, -1);
            if (wSkip > original) {
                // either INF (disconnected) or larger weight => critical
                critical.add(edgeIndex);
                continue; // if critical, it cannot be pseudo-critical
            }

            // 2) Check pseudo-critical: force include this edge
            long wForce = kruskal.apply(-1, edgeIndex);
            if (wForce == original) {
                pseudo.add(edgeIndex);
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        ans.add(critical);
        ans.add(pseudo);
        return ans;
    }
}
