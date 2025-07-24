class Solution {
    private List<List<Integer>> adj;
    private int[] subtreeXor;
    private int[] tin;
    private int[] tout;
    private int timer;

    
    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        this.adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        // Initialize arrays for pre-computation
        this.subtreeXor = new int[n];
        this.tin = new int[n];
        this.tout = new int[n];
        this.timer = 0;

        // DFS from root 0 to precompute subtreeXor and tin/tout for ancestry checks
        dfs(0, -1, nums);

        int totalXor = subtreeXor[0];
        int minScore = Integer.MAX_VALUE;

        // Iterate over all pairs of distinct nodes (i, j) except the root.
        // This corresponds to cutting the edge above i and the edge above j.
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int xorA, xorB, xorC;

                // Check ancestry relationship between i and j using tin/tout.
                // Case 1: i is a proper ancestor of j.
                if (tin[i] < tin[j] && tout[i] > tout[j]) {
                    xorA = subtreeXor[j];                  // Component 1: Subtree at j.
                    xorB = subtreeXor[i] ^ subtreeXor[j];  // Component 2: Subtree at i minus subtree at j.
                    xorC = totalXor ^ subtreeXor[i];       // Component 3: Rest of the tree.
                }
                // Case 2: j is a proper ancestor of i.
                else if (tin[j] < tin[i] && tout[j] > tout[i]) {
                    xorA = subtreeXor[i];                  // Component 1: Subtree at i.
                    xorB = subtreeXor[j] ^ subtreeXor[i];  // Component 2: Subtree at j minus subtree at i.
                    xorC = totalXor ^ subtreeXor[j];       // Component 3: Rest of the tree.
                }
                // Case 3: i and j are in disjoint branches.
                else {
                    xorA = subtreeXor[i];                  // Component 1: Subtree at i.
                    xorB = subtreeXor[j];                  // Component 2: Subtree at j.
                    xorC = totalXor ^ xorA ^ xorB;         // Component 3: Rest of the tree.
                }

                int maxVal = Math.max(xorA, Math.max(xorB, xorC));
                int minVal = Math.min(xorA, Math.min(xorB, xorC));
                minScore = Math.min(minScore, maxVal - minVal);
            }
        }

        return minScore;
    }

    /**
     * Performs DFS to compute subtree XOR sums and traversal times.
     * @param u Current node.
     * @param p Parent of current node.
     * @param nums Node values array.
     * @return The XOR sum of the subtree rooted at u.
     */
    private int dfs(int u, int p, int[] nums) {
        tin[u] = timer++;
        int currentXor = nums[u];
        for (int v : adj.get(u)) {
            if (v != p) {
                currentXor ^= dfs(v, u, nums);
            }
        }
        subtreeXor[u] = currentXor;
        tout[u] = timer++;
        return currentXor;
    }
}
