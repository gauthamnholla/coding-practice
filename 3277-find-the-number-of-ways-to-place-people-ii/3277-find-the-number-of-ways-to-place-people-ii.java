import java.util.Arrays;

class Solution {
    public int numberOfPairs(int[][] P) {
        // Step 1: Sort points
        // - By x descending
        // - If x is the same, by y ascending
        Arrays.parallelSort(P, (p, q) -> {
            return p[0] == q[0] ? (p[1] - q[1]) : (q[0] - p[0]);
        });

        final int n = P.length;
        int ans = 0;

        // Step 2: Iterate through each point as a starting candidate
        for (int i = 0; i < n - 1; i++) {
            int y = Integer.MAX_VALUE; // Keep track of lowest y found so far
            int yi = P[i][1];          // Current point's y

            // Step 3: Compare with points that come after in sorted order
            for (int j = i + 1; j < n; j++) {
                final int yj = P[j][1];

                // Condition:
                // - yj must be >= yi (cannot go below current point's y)
                // - yj must be < y (not "between" previous valid point and current)
                if (yj >= yi && y > yj) {
                    ans++;   // Found a valid pair
                    y = yj;  // Update the lowest valid y
                    if (yi == yj) break; // No better candidate possible
                }
            }
        }

        // Step 4: Return total number of valid pairs
        return ans;
    }
}
