class Solution {
    public int maxScore(List<List<Integer>> grid) {
        int n = grid.size(); // first column of nodes
        int m = 100; // second column of nodes
        int size = n + m + 2;
        var f = new DijkstraMinCostMaxFlow(size);

        for (int i = 1; i <= n; i++) { // connect the source to first column
            f.addEdge(0, i, 1, 0);
        }

        for (int i = 0; i < n; i++) { // interconnections between columns
            for (int j : grid.get(i)) {
                f.addEdge(i + 1, j + n, 1, 100 - j);
            }
        }

        for (int i = 1; i <= 100; i++) { // connect the second column to the sink
            f.addEdge(i + n, size - 1, 1, 0);
        }

        int[] mf = f.minCostMaxFlow(0, size - 1);
        return 100 * mf[0] - mf[1]; // needed to convert back, sum(100-x) --> sum(x)
    }
}

public class DijkstraMinCostMaxFlow {

    static class Edge {
        int from, to, capacity, cost, flow;
        Edge reverse;

        public Edge(int from, int to, int capacity, int cost) {
            this.from = from;
            this.to = to;
            this.capacity = capacity;
            this.cost = cost;
            this.flow = 0;
        }
    }

    private int n;
    private List<Edge>[] graph;
    private int[] potential;

    public DijkstraMinCostMaxFlow(int n) {
        this.n = n;
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        potential = new int[n];
    }

    public void addEdge(int from, int to, int capacity, int cost) {
        Edge forward = new Edge(from, to, capacity, cost);
        Edge reverse = new Edge(to, from, 0, -cost);
        forward.reverse = reverse;
        reverse.reverse = forward;
        graph[from].add(forward);
        graph[to].add(reverse);
    }

    private boolean dijkstra(int source, int sink, int[] dist, Edge[] parentEdge) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[] { source, 0 });

        while (!pq.isEmpty()) {
            int[] u = pq.poll();
            int current = u[0];
            int currentDist = u[1];

            if (currentDist > dist[current])
                continue;

            for (Edge edge : graph[current]) {
                int next = edge.to;
                int newDist = dist[current] + edge.cost + potential[current] - potential[next];

                if (edge.flow < edge.capacity && dist[next] > newDist) {
                    dist[next] = newDist;
                    parentEdge[next] = edge;
                    pq.add(new int[] { next, dist[next] });
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (dist[i] < Integer.MAX_VALUE) {
                potential[i] += dist[i];
            }
        }

        return dist[sink] != Integer.MAX_VALUE;
    }

    public int[] minCostMaxFlow(int source, int sink) {
        int maxFlow = 0, minCost = 0;
        int[] dist = new int[n];
        Edge[] parentEdge = new Edge[n];

        Arrays.fill(potential, 0);

        while (dijkstra(source, sink, dist, parentEdge)) {
            int flow = Integer.MAX_VALUE;
            for (int v = sink; v != source; v = parentEdge[v].from) {
                flow = Math.min(flow, parentEdge[v].capacity - parentEdge[v].flow);
            }

            for (int v = sink; v != source; v = parentEdge[v].from) {
                parentEdge[v].flow += flow;
                parentEdge[v].reverse.flow -= flow;
                minCost += flow * parentEdge[v].cost;
            }

            maxFlow += flow;
        }

        return new int[] { maxFlow, minCost };
    }
}