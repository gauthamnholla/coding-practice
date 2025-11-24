class Solution {
    public int numTrees(int n) {
        // dp[i] = number of unique BSTs with i nodes
        int[] dp = new int[n + 1];

        dp[0] = 1; // empty tree
        dp[1] = 1; // single node

        // fill dp for 2..n
        for (int nodes = 2; nodes <= n; nodes++) {
            int total = 0;
            // choose root from 1..nodes
            for (int root = 1; root <= nodes; root++) {
                int left = root - 1;         // nodes in left subtree
                int right = nodes - root;    // nodes in right subtree
                total += dp[left] * dp[right];
            }
            dp[nodes] = total;
        }

        return dp[n];
    }
}
