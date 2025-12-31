class Solution {
    private static final int MOD = 1000000007;
    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length;
        List<List<Integer>> adj = new ArrayList<>(n+2);
        for (int i = 0; i <= n+1; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{1, 0});
        boolean[] vis = new boolean[n+2];
        vis[1] = true;
        int maxDepth = 0;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int u = cur[0], d = cur[1];
            maxDepth = Math.max(maxDepth, d);
            for (int v : adj.get(u)) {
                if (!vis[v]) {
                    vis[v] = true;
                    q.offer(new int[]{v, d + 1});
                }
            }
        }
        if (maxDepth == 0) return 0;
        return modPow(2, maxDepth - 1);
    }
    private int modPow(long base, int exp) {
        long res = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = (res * base) % MOD;
            }
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return (int) res;
    }
}