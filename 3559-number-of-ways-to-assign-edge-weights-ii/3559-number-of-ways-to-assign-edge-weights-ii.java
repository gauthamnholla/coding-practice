class Solution {
    private static final int MOD = 1_000_000_007;

    // Tree structure & LCA precomputation
    private List<Integer>[] tree;
    private int[][] lifting;     // Binary lifting table
    private int[] dist;          // Distance from root (depth)
    private int[] startTime;     // Entry time in DFS (for ancestor check)
    private int[] endTime;       // Exit time in DFS
    private int time, nodes;

    // DP table to count valid weight assignments
    private int[][] dp;

    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        initTree(edges); // Preprocess LCA and tree structure

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0], v = queries[i][1];
            int pathLength = getPathLength(u, v);
            result[i] = countOddWeightAssignments(pathLength); // Solve for this query
        }
        return result;
    }

    // Preprocessing the tree structure and LCA using binary lifting
    private void initTree(int[][] edges) {
        nodes = edges.length + 1; // Total nodes = edges + 1 (since it's a tree)

        // Initialize tree as adjacency list
        tree = new ArrayList[nodes + 1];
        for (int i = 0; i <= nodes; i++) tree[i] = new ArrayList<>();

        // Build the undirected tree
        for (int[] edge : edges) {
            tree[edge[0]].add(edge[1]);
            tree[edge[1]].add(edge[0]);
        }

        int maxLog = (int) Math.ceil(Math.log(nodes) / Math.log(2)) + 1;
        lifting = new int[nodes + 1][maxLog];
        dist = new int[nodes + 1];
        startTime = new int[nodes + 1];
        endTime = new int[nodes + 1];
        time = 0;

        // DFS to compute lifting table and entry/exit times
        dfsLCA(1, 1, 0);

        // Initialize memo table for DP
        dp = new int[2][nodes + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
    }

    // DFS to compute depth, entry/exit times, and binary lifting table
    private void dfsLCA(int node, int parent, int depth) {
        dist[node] = depth;
        startTime[node] = time++;
        lifting[node][0] = parent;

        // Fill in binary lifting table for this node
        for (int i = 1; i < lifting[0].length; i++) {
            lifting[node][i] = lifting[lifting[node][i - 1]][i - 1];
        }

        // Explore children (standard DFS)
        for (int child : tree[node]) {
            if (child != parent) {
                dfsLCA(child, node, depth + 1);
            }
        }

        endTime[node] = time++;
    }

    // Get the length of the path (number of edges) between two nodes
    private int getPathLength(int u, int v) {
        int lca = getLCA(u, v);
        return dist[u] + dist[v] - 2 * dist[lca];
    }

    // Find lowest common ancestor (LCA) using binary lifting
    private int getLCA(int u, int v) {
        if (isAncestor(u, v)) return u;
        if (isAncestor(v, u)) return v;

        // Lift u up until it becomes an ancestor of v
        for (int i = lifting[0].length - 1; i >= 0; i--) {
            if (!isAncestor(lifting[u][i], v)) {
                u = lifting[u][i];
            }
        }
        return lifting[u][0];
    }

    // Returns true if `u` is an ancestor of `v`
    private boolean isAncestor(int u, int v) {
        return startTime[u] <= startTime[v] && endTime[u] >= endTime[v];
    }

    // Count number of valid ways to assign 1 or 2 weights to k edges such that total sum is odd
    private int countOddWeightAssignments(int k) {
        return dfsWeightWays(k, 0);
    }

    // DP to count the number of ways to fill `k` positions with 1 or 2 such that final sum is odd
    private int dfsWeightWays(int remaining, int currentSum) {
        // Base case: all weights assigned
        if (remaining == 0) {
            return (currentSum % 2 == 1) ? 1 : 0;
        }

        // Memoized result
        if (dp[currentSum % 2][remaining] != -1) return dp[currentSum % 2][remaining];

        // Try assigning weight 1 and weight 2 to current edge
        long waysWith1 = dfsWeightWays(remaining - 1, currentSum + 1);
        long waysWith2 = dfsWeightWays(remaining - 1, currentSum + 2);

        // Store and return result modulo MOD
        return dp[currentSum % 2][remaining] = (int) ((waysWith1 + waysWith2) % MOD);
    }
}