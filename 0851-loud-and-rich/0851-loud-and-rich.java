class Solution {
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        // build graph: edge u -> v means v is richer than u
        for (int[] r : richer) {
            int richerPerson = r[0], poorerPerson = r[1];
            graph[poorerPerson].add(richerPerson);
        }

        int[] ans = new int[n];
        Arrays.fill(ans, -1); // -1 means not computed

        // DFS that returns the quietest person reachable from u (including u)
        for (int i = 0; i < n; i++) {
            dfs(i, graph, quiet, ans);
        }
        return ans;
    }

    private int dfs(int u, List<Integer>[] graph, int[] quiet, int[] ans) {
        if (ans[u] != -1) return ans[u]; // already computed
        ans[u] = u; // at least u itself
        for (int v : graph[u]) {
            int cand = dfs(v, graph, quiet, ans);
            if (quiet[cand] < quiet[ans[u]]) {
                ans[u] = cand; // found quieter person among richer people
            }
        }
        return ans[u];
    }
}
