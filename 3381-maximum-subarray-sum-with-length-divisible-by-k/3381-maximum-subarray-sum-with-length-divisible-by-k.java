import java.util.*;

class Solution {
    public long maxSubarraySum(int[] nums, int k) {
        int n = nums.length;

        long[] minPref = new long[k];
        Arrays.fill(minPref, Long.MAX_VALUE / 4);

        long pref = 0;
        long ans = Long.MIN_VALUE;

        // prefix sum before starting (index 0) → remainder 0
        minPref[0] = 0;

        for (int i = 1; i <= n; i++) {
            pref += nums[i - 1];     // prefix sum up to index i
            int r = i % k;           // remainder class

            // try forming subarray ending at i
            ans = Math.max(ans, pref - minPref[r]);

            // update min prefix for this remainder
            minPref[r] = Math.min(minPref[r], pref);
        }

        return ans;
    }
}
