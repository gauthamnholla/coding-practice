import java.util.*;

class Solution {
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edgesList) {
        // convert to int[][] if that's the input form, but we assume edgesList here
        int[] indeg = new int[n];
        for (List<Integer> e : edgesList) {
            indeg[e.get(1)]++;
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (indeg[i] == 0) ans.add(i);
        }
        return ans;
    }

    // If input is int[][] edges, use this overload:
    public List<Integer> findSmallestSetOfVertices(int n, int[][] edges) {
        int[] indeg = new int[n];
        for (int[] e : edges) indeg[e[1]]++;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) if (indeg[i] == 0) ans.add(i);
        return ans;
    }
}
