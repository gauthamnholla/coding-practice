import java.util.*;

public class Solution {
    public boolean isPossible(int n, List<List<Integer>> edges) {

        List<Set<Integer>> adj = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) adj.add(new HashSet<>());

        int[] deg = new int[n + 1];

        for (List<Integer> e : edges) {
            int a = e.get(0), b = e.get(1);
            adj.get(a).add(b);
            adj.get(b).add(a);
            deg[a]++; 
            deg[b]++;
        }

        List<Integer> odd = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if ((deg[i] & 1) == 1) odd.add(i);
        }

        int m = odd.size();
        if (m == 0) return true;
        if (m > 4) return false;

        if (m == 2) {
            int u = odd.get(0), v = odd.get(1);

            if (!adj.get(u).contains(v)) return true;

            for (int w = 1; w <= n; w++) {
                if (w == u || w == v) continue;
                if (!adj.get(u).contains(w) && !adj.get(v).contains(w)) return true;
            }
            return false;
        }

        int a = odd.get(0), b = odd.get(1), c = odd.get(2), d = odd.get(3);

        if (!adj.get(a).contains(b) && !adj.get(c).contains(d)) return true;
        if (!adj.get(a).contains(c) && !adj.get(b).contains(d)) return true;
        if (!adj.get(a).contains(d) && !adj.get(b).contains(c)) return true;

        return false;
    }
}
