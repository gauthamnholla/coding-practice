import java.util.*;

class Solution {
    public int longestPath(int[] parent, String s) {
        int n = parent.length;
        // build children adjacency list
        List<Integer>[] children = new ArrayList[n];
        for (int i = 0; i < n; i++) children[i] = new ArrayList<>();
        for (int i = 1; i < n; i++) children[parent[i]].add(i);

        // bestDown[u] = length of longest valid path starting at u and going down
        int[] bestDown = new int[n];
        Arrays.fill(bestDown, 1); // at least the node itself

        int ans = 1;

        // iterative post-order: (node, visitedFlag)
        Deque<int[]> st = new ArrayDeque<>();
        st.push(new int[]{0, 0});
        while (!st.isEmpty()) {
            int[] cur = st.pop();
            int u = cur[0], visited = cur[1];
            if (visited == 0) {
                // push again as visited, then push children
                st.push(new int[]{u, 1});
                for (int ch : children[u]) st.push(new int[]{ch, 0});
            } else {
                // process node after children are done
                int max1 = 0, max2 = 0; // top two child-down lengths valid to attach
                for (int ch : children[u]) {
                    if (s.charAt(ch) == s.charAt(u)) continue; // cannot attach this child
                    int len = bestDown[ch]; // length starting at child
                    if (len > max1) { max2 = max1; max1 = len; }
                    else if (len > max2) { max2 = len; }
                }
                // best path that goes down from u: include u plus best child
                bestDown[u] = 1 + max1;
                // best path that goes through u (child -> u -> child): 1 + max1 + max2
                ans = Math.max(ans, 1 + max1 + max2);
            }
        }

        return ans;
    }
}
