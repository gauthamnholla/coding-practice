import java.util.*;

class Solution {
    public int minScore(int n, int[][] roads) {
        // adjacency list: for 1..n
        List<int[]>[] adj = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) adj[i] = new ArrayList<>();

        for (int[] r : roads) {
            int a = r[0], b = r[1], w = r[2];
            adj[a].add(new int[]{b, w});
            adj[b].add(new int[]{a, w});
        }

        boolean[] seen = new boolean[n + 1];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        seen[1] = true;

        int minEdge = Integer.MAX_VALUE;

        while (!stack.isEmpty()) {
            int u = stack.pop();
            for (int[] nb : adj[u]) {
                int v = nb[0], w = nb[1];
                // update global minimum for any edge adjacent to this component node
                minEdge = Math.min(minEdge, w);
                if (!seen[v]) {
                    seen[v] = true;
                    stack.push(v);
                }
            }
        }

        return minEdge;
    }
}
