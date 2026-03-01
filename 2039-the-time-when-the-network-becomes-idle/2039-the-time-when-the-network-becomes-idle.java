class Solution {
    private int getLastSend(int d, int p) {
        if (p >= 2 * d) {
            return 0;
        }
        if ((2 * d) % p == 0) {
            return 2 * d - p;
        }
        return 2 * d - (2 * d) % p;
    }

    public int networkBecomesIdle(int[][] edges, int[] patience) {
        // Build adjacency list.
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < patience.length; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // Perform BFS to find the min distance of each node from the root.
        int[] distance = new int[patience.length];
        boolean[] visited = new boolean[patience.length];
        visited[0] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while (!queue.isEmpty()) {
            int node = queue.remove();
            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    distance[neighbor] = 1 + distance[node];
                    queue.add(neighbor);
                    visited[neighbor] = true;
                }
            }
        }

        int maxTime = 0;
        for (int n = 1; n < patience.length; n++) {
            int lastSend = getLastSend(distance[n], patience[n]);
            int lastReceive = lastSend + 2 * distance[n];
            maxTime = Math.max(maxTime, lastReceive);
        }
        return maxTime + 1;
    }
}