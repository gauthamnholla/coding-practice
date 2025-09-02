import java.util.*;

class Solution {
    public int numberOfPairs(int[][] points) {
        // Step 1: Sort points
        // - By x coordinate in ascending order
        // - If x is the same, by y coordinate in descending order
        Arrays.sort(points, (a, b) -> {
            if (a[0] == b[0]) return Integer.compare(b[1], a[1]);
            return Integer.compare(a[0], b[0]);
        });

        int n = points.length;
        int result = 0;

        // Step 2: Iterate over all starting points
        for (int i = 0; i < n; i++) {
            int top = points[i][1];       // Highest y value for this group
            int bot = Integer.MIN_VALUE;  // Track lowest valid y so far

            // Step 3: Compare with subsequent points
            for (int j = i + 1; j < n; j++) {
                int y = points[j][1];

                // Valid pair if:
                // - y is greater than bot (strictly above previous bottom)
                // - y is not higher than top
                if (bot < y && y <= top) {
                    result++;   // Found a valid pair
                    bot = y;    // Update new bottom
                    if (bot == top) break; // No better y possible
                }
            }
        }

        // Step 4: Return total pairs found
        return result;
    }
}
