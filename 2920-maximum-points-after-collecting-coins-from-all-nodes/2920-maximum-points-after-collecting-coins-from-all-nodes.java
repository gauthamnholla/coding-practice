class Solution {
    public int maximumPoints(int[][] edges, int[] coins, int k) {
        int n = coins.length;
        List<List<Integer>> graph = new ArrayList<>();

        
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] e : edges) {
            int u = e[0], v = e[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

       
        Integer[][] dp = new Integer[n][15];  
        return dfs(0, -1, 0, graph, coins, k, dp);
    }

    private int dfs(int node, int parent, int red, List<List<Integer>> graph, int[] coins, int k, Integer[][] dp) {
        if (red >= 14) return 0;

        if (dp[node][red] != null) return dp[node][red];

        int currCoins = coins[node] / (1 << red);  
        int way1 = currCoins - k;
        int way2 = currCoins / 2;

        for (int neighbor : graph.get(node)) {
            if (neighbor == parent) continue; 

            way1 += dfs(neighbor, node, red, graph, coins, k, dp);     
            way2 += dfs(neighbor, node, red + 1, graph, coins, k, dp);  
        }

        return dp[node][red] = Math.max(way1, way2);
    }
}