class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // Special case: single node
        if (n == 1) return Collections.singletonList(0);

        // Build adjacency list and degree count
        List<Integer>[] graph = new ArrayList[n];
        int[] degree = new int[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            graph[a].add(b);
            graph[b].add(a);
            degree[a]++; degree[b]++;
        }

        // Initialize leaves (nodes with degree 1)
        Queue<Integer> leaves = new ArrayDeque<>();
        for (int i = 0; i < n; i++) if (degree[i] == 1) leaves.offer(i);

        // Trim leaves layer by layer until <= 2 nodes remain
        int remaining = n;
        while (remaining > 2) {
            int size = leaves.size();
            remaining -= size; // we'll remove these leaves
            for (int i = 0; i < size; i++) {
                int leaf = leaves.poll();
                for (int neigh : graph[leaf]) {
                    degree[neigh]--;           // remove edge leaf-neigh
                    if (degree[neigh] == 1)   // new leaf
                        leaves.offer(neigh);
                }
            }
        }

        // Remaining nodes are MHT roots
        return new ArrayList<>(leaves);
    }
}
