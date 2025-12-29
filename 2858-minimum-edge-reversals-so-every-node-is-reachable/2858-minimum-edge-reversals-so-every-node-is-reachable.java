class Solution {
    //A tree with n nodes has exactly n-1 edges, and there's exactly one path between any two nodes.
    
    private List<int[]>[] graph;
    private int[] result;
    
    public int[] minEdgeReversals(int n, int[][] edges) {
        // Build bidirectional graph with edge costs
        // cost = 0 for original direction, cost = 1 for reverse direction
        graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph[u].add(new int[]{v, 0}); // original edge, no cost
            graph[v].add(new int[]{u, 1}); // reverse edge, cost = 1
        }
        
        result = new int[n];
        
        // Step 1: Calculate result for node 0 using DFS
        boolean[] visited = new boolean[n];
        result[0] = dfs(0, visited);
        
        // Step 2: Re-root to calculate results for all other nodes
        Arrays.fill(visited, false);
        reroot(0, visited);
        
        return result;
    }
    
    // Calculate minimum reversals needed when starting from node 'start'
    private int dfs(int node, boolean[] visited) {
        visited[node] = true;
        int reversals = 0;
        
        for (int[] neighbor : graph[node]) {
            int nextNode = neighbor[0];
            int cost = neighbor[1];
            
            if (!visited[nextNode]) {
                reversals += cost + dfs(nextNode, visited);
            }
        }
        
        return reversals;
    }
    
    // Re-root the tree to calculate results for all nodes
    private void reroot(int node, boolean[] visited) {
        visited[node] = true;
        
        for (int[] neighbor : graph[node]) {
            int nextNode = neighbor[0];
            int cost = neighbor[1];
            
            if (!visited[nextNode]) {
                // When we move the root from 'node' to 'nextNode':
                // - If original edge was node->nextNode (cost=0), now we need nextNode->node (cost=1)
                //   result[nextNode] = result[node] + 1
                // - If original edge was nextNode->node (cost=1), now we need node->nextNode (cost=0)
                //   result[nextNode] = result[node] - 1
                // So the change in cost is: 1 - 2*cost
                result[nextNode] = result[node] + 1 - 2 * cost;
                reroot(nextNode, visited);
            }
        }
    }
}