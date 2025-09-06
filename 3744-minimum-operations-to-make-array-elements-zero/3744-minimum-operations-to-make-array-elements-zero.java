class Solution {
    public long minOperations(int[][] queries) {
        long ans = 0; // Final answer (sum of operations for all queries)

        // Process each query range [l, r]
        for (int[] q : queries) {
            int l = q[0], r = q[1]; // Range boundaries
            long S = 0;             // Total sum of required steps for numbers in range
            int dMax = 0;           // Maximum steps required for any number in range

            // Iterate over bit-length groups (1 to 31 bits covers all int values)
            for (int k = 1; k <= 31; k++) {
                long low = 1L << (k - 1);   // Smallest number with k bits
                long high = (1L << k) - 1;  // Largest number with k bits

                if (low > r) break; // If smallest number already exceeds range, stop

                // Clip the interval [low, high] to fit inside [l, r]
                long a = Math.max((long) l, low);
                long b = Math.min((long) r, high);

                if (a > b) continue; // No overlap, skip this k

                // Count how many numbers fall into this bit-length range
                long cnt = b - a + 1;

                // Steps needed for numbers with k bits = ceil(k/2)
                int stepsForK = (k + 1) / 2;

                // Add contribution: cnt * stepsForK
                S += cnt * stepsForK;

                // Track maximum steps needed across numbers
                dMax = Math.max(dMax, stepsForK);
            }

            // For this query:
            // ops = max(max steps needed, ceil(total steps/2))
            long ops = Math.max((long) dMax, (S + 1) / 2);

            // Add to final answer
            ans += ops;
        }

        return ans;
    }
}
