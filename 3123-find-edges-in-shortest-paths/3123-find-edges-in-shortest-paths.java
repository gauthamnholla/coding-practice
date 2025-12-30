class Solution {
    class Edge {
        int node;
        int weight;
        Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    public boolean[] findAnswer(int n, int[][] edges) {
        // Initialize graph as an array of lists
        List<Edge>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(new Edge(edge[1], edge[2]));
            graph[edge[1]].add(new Edge(edge[0], edge[2]));
        }

        // Dijkstra's algorithm to find the shortest path distances
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        pq.offer(new Edge(0, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int currentNode = current.node;
            int currentWeight = current.weight;

            if (currentWeight > dist[currentNode]) continue;

            for (Edge neighbor : graph[currentNode]) {
                int newDist = dist[currentNode] + neighbor.weight;
                if (newDist < dist[neighbor.node]) {
                    dist[neighbor.node] = newDist;
                    pq.offer(new Edge(neighbor.node, newDist));
                }
            }
        }

        // Use a HashSet to track edges that are part of any shortest path
        Set<Long> shortestPathEdges = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n - 1);

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            for (Edge neighbor : graph[currentNode]) {
                if (dist[currentNode] - neighbor.weight == dist[neighbor.node]) {
                    long edgeKey = encodeEdge(neighbor.node, currentNode);
                    if (!shortestPathEdges.contains(edgeKey)) {
                        shortestPathEdges.add(edgeKey);
                        queue.add(neighbor.node);
                    }
                }
            }
        }

        // Generate the final answer
        boolean[] answer = new boolean[edges.length];
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0], v = edges[i][1];
            long edgeForward = encodeEdge(u, v);
            long edgeBackward = encodeEdge(v, u);
            answer[i] = shortestPathEdges.contains(edgeForward) || shortestPathEdges.contains(edgeBackward);
        }

        return answer;
    }

    // Helper function to encode an edge (u, v) as a unique long key
    private long encodeEdge(int u, int v) {
        return ((long) u << 32) | (v & 0xFFFFFFFFL);
    }
}