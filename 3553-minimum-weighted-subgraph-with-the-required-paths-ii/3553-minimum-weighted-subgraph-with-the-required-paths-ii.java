class Solution {
    private int maxDepth = 0, maxPower = 0;
    private int[] depth, dist;
    private int[][] lift;
    private ArrayList<int[]>[] adj;
    public int[] minimumWeight(int[][] edges, int[][] queries) {
        int n = edges.length + 1, m = queries.length;
        lift = new int[16][n];
        depth = new int[n];
        dist = new int[n];
        adj = new ArrayList[n];
        for(int i = 0; i < n; ++i) adj[i] = new ArrayList<int[]>();
        for(int[] edge : edges) {
            int a = edge[0], b = edge[1], c = edge[2];
            adj[a].add(new int[] {b, c});
            adj[b].add(new int[] {a, c});
        }

        dfs(0, 0, 0); //calc depth and root-to-node distance for each node
        initLifting(n); //set up lifting array

        int[] ans = new int[m];
        for(int i = 0; i < m; ++i) {
            int a = queries[i][0], b = queries[i][1], c = queries[i][2];
            ans[i] = dist[a] + dist[b] + dist[c] - dist[lca(a, b)] - dist[lca(b, c)] - dist[lca(c, a)];
        }
        return ans;
    }
    private void initLifting(int n) {
        //standard binary lifting, lift[i][j] = the (2^i)th ancestor of node j
        for(int i = 1; (1 << i) <= maxDepth; ++i) {
            maxPower++;
            for(int j = 0; j < n; ++j) lift[i][j] = lift[i - 1][lift[i - 1][j]];
        }
    }
    private void dfs(int index, int prev, int d) {
        if(d > maxDepth) maxDepth = d; //track total max depth
        depth[index] = d; //track each node's depth
        lift[0][index] = prev; //set this node's 1st ancestor to it's parent 
        for(int[] next : adj[index]) {
            int a = next[0];
            if(a == prev) continue;
            dist[a] = dist[index] + next[1]; //track root to node weight sum
            dfs(a, index, d + 1);
        }
    }
    private int lca(int a, int b) { //Lowest Common Ancestor of nodes a and b
        int diff = depth[a] - depth[b];
        if(diff < 0) { //always make a the lower node and b the higher node for simplicity
            int temp = a;
            a = b;
            b = temp;
            diff = -diff;
        }
        //set a to it's ancestor with the same depth as b
        for(int i = 0; diff > 0; ++i) {
            if((diff & 1) == 1) a = lift[i][a];
            diff >>= 1;
        }
        if(a == b) return b; //if b is an ancestor of a, return early

        //if their (2^i)th ancestor differs, we can safely set a and b to their respective (2^i)th ancestor
        for(int i = maxPower; i >= 0; --i) {
            if(lift[i][a] != lift[i][b]) {
                a = lift[i][a];
                b = lift[i][b];
            }
        }
        return lift[0][a]; //we've found the ancestors of a and b which share the same parent, return the shared parent
    }
}