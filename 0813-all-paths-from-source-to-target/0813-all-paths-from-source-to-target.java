import java.util.*;

class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int n = graph.length;
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(0);                     // path always starts at node 0
        dfs(0, n - 1, graph, path, ans);
        return ans;
    }

    private void dfs(int node, int target, int[][] graph, List<Integer> path, List<List<Integer>> ans) {
        if (node == target) {
            // reached target, add a copy of the current path
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int nei : graph[node]) {
            path.add(nei);
            dfs(nei, target, graph, path, ans);
            path.remove(path.size() - 1); // backtrack
        }
    }
}
