import java.util.*;

class Solution {
    static final long MOD = 1_000_000_007;

    public int sumDistance(int[] nums, String s, int d) {
        int n = nums.length;
        long[] pos = new long[n];

        // Step 1: compute final positions
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'L') pos[i] = nums[i] - (long)d;
            else pos[i] = nums[i] + (long)d;
        }

        // Step 2: sort positions
        Arrays.sort(pos);

        // Step 3: compute pairwise distance sum using prefix logic
        long ans = 0;
        long prefix = 0;

        for (int i = 0; i < n; i++) {
            ans = (ans + pos[i] * i - prefix) % MOD;
            prefix += pos[i];
        }

        return (int)((ans % MOD + MOD) % MOD);
    }
}
