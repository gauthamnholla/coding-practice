import java.util.*;

class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        // Build adjacency lists for red and blue edges
        List<Integer>[] redAdj = new ArrayList[n];
        List<Integer>[] blueAdj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            redAdj[i] = new ArrayList<>();
            blueAdj[i] = new ArrayList<>();
        }
        for (int[] e : redEdges) redAdj[e[0]].add(e[1]);
        for (int[] e : blueEdges) blueAdj[e[0]].add(e[1]);

        // distances: distRed[v] = distance to v when last edge used is red
        //            distBlue[v] = distance to v when last edge used is blue
        int[] distRed = new int[n];
        int[] distBlue = new int[n];
        Arrays.fill(distRed, -1);
        Arrays.fill(distBlue, -1);

        // BFS queue of (node, lastColor) where lastColor: 0=red, 1=blue
        Deque<int[]> q = new ArrayDeque<>();

        // Start from node 0: allow both colors as "last color" with distance 0
        distRed[0] = 0;
        distBlue[0] = 0;
        q.add(new int[] {0, 0});
        q.add(new int[] {0, 1});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int u = cur[0];
            int lastColor = cur[1];
            int curDist = (lastColor == 0) ? distRed[u] : distBlue[u];

            // we must take edges of opposite color
            if (lastColor == 0) {
                // last was red -> take blue edges next
                for (int v : blueAdj[u]) {
                    if (distBlue[v] == -1) {
                        distBlue[v] = curDist + 1;
                        q.add(new int[] {v, 1});
                    }
                }
            } else {
                // last was blue -> take red edges next
                for (int v : redAdj[u]) {
                    if (distRed[v] == -1) {
                        distRed[v] = curDist + 1;
                        q.add(new int[] {v, 0});
                    }
                }
            }
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            if (distRed[i] == -1 && distBlue[i] == -1) ans[i] = -1;
            else if (distRed[i] == -1) ans[i] = distBlue[i];
            else if (distBlue[i] == -1) ans[i] = distRed[i];
            else ans[i] = Math.min(distRed[i], distBlue[i]);
        }
        return ans;
    }
}
