import java.util.*;

class Solution {
    static final long MOD = 1_000_000_007L;

    public int waysToBuildRooms(int[] prev) {
        int n = prev.length;

        // Build tree: children lists
        List<Integer>[] ch = new ArrayList[n];
        for (int i = 0; i < n; i++) ch[i] = new ArrayList<>();
        for (int i = 1; i < n; i++) ch[prev[i]].add(i);

        // Precompute factorials and inverse factorials for nCk
        long[] fact = new long[n + 1], invFact = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) fact[i] = fact[i - 1] * i % MOD;

        // inverse factorial using Fermat's theorem
        invFact[n] = modPow(fact[n], MOD - 2);
        for (int i = n - 1; i >= 0; i--) invFact[i] = invFact[i + 1] * (i + 1) % MOD;

        // Arrays to store subtree sizes and number of ways
        int[] size = new int[n];
        long[] ways = new long[n];

        // Iterative post-order DFS: (node, visitedFlag)
        Deque<int[]> st = new ArrayDeque<>();
        st.push(new int[]{0, 0});

        while (!st.isEmpty()) {
            int[] cur = st.pop();
            int u = cur[0], visited = cur[1];

            if (visited == 0) {
                // First time we see node: push again marked as visited
                st.push(new int[]{u, 1});
                // Then explore children
                for (int c : ch[u]) st.push(new int[]{c, 0});
            } else {
                // Now all children are processed → compute ways for this node
                long w = 1;
                int total = 0;

                for (int c : ch[u]) {
                    // Multiply by ways of child's subtree
                    w = (w * ways[c]) % MOD;

                    // Count how many ways to interleave child's subtree with previous ones
                    long comb = fact[total + size[c]] 
                              * invFact[size[c]] % MOD 
                              * invFact[total] % MOD;

                    w = (w * comb) % MOD;

                    total += size[c]; // increase total subtree size
                }

                size[u] = total + 1; // size of this subtree
                ways[u] = w;         // ways for this subtree
            }
        }

        return (int) ways[0]; // result is number of ways for the root
    }

    // Fast modular exponentiation (for modular inverse)
    private long modPow(long a, long e) {
        long r = 1;
        while (e > 0) {
            if ((e & 1) == 1) r = r * a % MOD;
            a = a * a % MOD;
            e >>= 1;
        }
        return r;
    }
}
