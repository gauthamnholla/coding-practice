import java.util.*;

class Graph {
    List<int[]>[] adj;  // adjacency list: each entry = {to, cost}
    int n;

    public Graph(int n, int[][] edges) {
        this.n = n;
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            adj[e[0]].add(new int[]{e[1], e[2]});
        }
    }
    
    public void addEdge(int[] edge) {
        int from = edge[0], to = edge[1], cost = edge[2];
        adj[from].add(new int[]{to, cost});
    }
    
    public int shortestPath(int node1, int node2) {
        // Dijkstra's algorithm
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        dist[node1] = 0;
        pq.offer(new int[]{node1, 0});
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[0], cost = cur[1];
            
            if (u == node2) return cost;  // early stop
            
            if (cost > dist[u]) continue;
            
            for (int[] edge : adj[u]) {
                int v = edge[0], w = edge[1];
                if (dist[v] > cost + w) {
                    dist[v] = cost + w;
                    pq.offer(new int[]{v, dist[v]});
                }
            }
        }
        
        return -1; // no path
    }
}
