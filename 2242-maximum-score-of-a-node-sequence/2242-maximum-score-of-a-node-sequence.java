class Solution {
        public int maximumScore(int[] A, int[][] edges) {
        int n = A.length;
        PriorityQueue<Integer>[] q = new PriorityQueue[n];
        for (int i = 0; i < n; i++)
            q[i] = new PriorityQueue<>((a, b) -> A[a] - A[b]);
        for (int[] e : edges) {
            q[e[0]].offer(e[1]);
            q[e[1]].offer(e[0]);
            if (q[e[0]].size() > 3) q[e[0]].poll();
            if (q[e[1]].size() > 3) q[e[1]].poll();
        }
        int res = -1;
        for (int[] edge : edges)
            for (int i : q[edge[0]])
                for (int j : q[edge[1]])
                    if (i != j && i != edge[1] && j != edge[0])
                        res = Math.max(res, A[i] + A[j] + A[edge[0]] + A[edge[1]]);  
        return res;
    }
}