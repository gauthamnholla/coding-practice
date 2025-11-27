import java.util.Arrays;

public class Solution {
    public int maxProfit(int n, int[][] edges, int[] score) {
        // build prerequisite mask: need[i] has bit j set if j must come before i
        int[] need = new int[n];
        for (int[] e : edges) {
            int a = e[0], b = e[1]; // a must be before b
            need[b] |= (1 << a);
        }

        int ALL = 1 << n;
        long NEG = Long.MIN_VALUE / 4; // sentinel for unreachable
        long[] dp = new long[ALL];
        Arrays.fill(dp, NEG);
        dp[0] = 0;

        // optional: precompute pos (position multiplier = number already chosen + 1)
        int[] pos = new int[ALL];
        for (int mask = 0; mask < ALL; ++mask) pos[mask] = Integer.bitCount(mask) + 1;

        for (int mask = 0; mask < ALL; ++mask) {
            if (dp[mask] == NEG) continue; // unreachable state
            int p = pos[mask];
            for (int i = 0; i < n; ++i) {
                // if i not yet chosen and all its prerequisites are in mask
                if (((mask >> i) & 1) == 0 && (mask & need[i]) == need[i]) {
                    int mask2 = mask | (1 << i);
                    long gain = (long) score[i] * p;
                    dp[mask2] = Math.max(dp[mask2], dp[mask] + gain);
                }
            }
        }

        long ans = dp[ALL - 1];
        if (ans == NEG) return -1; // impossible (cycle / cannot schedule all)
        // safe to cast to int if problem constraints expect int result
        return (int) ans;
    }
}
