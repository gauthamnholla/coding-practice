class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        List<Integer>[] rev = new ArrayList[n];   // reverse adjacency
        for (int i = 0; i < n; i++) rev[i] = new ArrayList<>();
        int[] outdeg = new int[n];               // outdeg[i] = number of edges from i

        // build reverse graph and outdegree array
        for (int u = 0; u < n; u++) {
            outdeg[u] = graph[u].length;
            for (int v : graph[u]) rev[v].add(u);
        }

        // start from terminal nodes (outdeg == 0)
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) if (outdeg[i] == 0) q.offer(i);

        // nodes that can be reduced to terminals are safe
        boolean[] safe = new boolean[n];
        while (!q.isEmpty()) {
            int cur = q.poll();
            safe[cur] = true;                     // cur is safe (terminal or leads to terminal)
            for (int prev : rev[cur]) {
                outdeg[prev]--;                   // remove edge prev->cur
                if (outdeg[prev] == 0) q.offer(prev); // now prev becomes safe candidate
            }
        }

        // collect safe nodes in ascending order
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) if (safe[i]) res.add(i);
        return res;
    }
}
