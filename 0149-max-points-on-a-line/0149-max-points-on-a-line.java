class Solution {
    public int maxPoints(int[][] points) {
        if (points.length <= 2) return points.length;

        int ans = 0;

        for (int i = 0; i < points.length; ++i) {
            Map<Pair<Integer, Integer>, Integer> slopeCount = new HashMap<>();
            int samePoints = 1; // Count of identical points to point i
            int maxPoints = 0;  // Max points with same slope from point i

            for (int j = i + 1; j < points.length; ++j) {
                int[] p1 = points[i];
                int[] p2 = points[j];

                if (p1[0] == p2[0] && p1[1] == p2[1]) {
                    ++samePoints;
                } else {
                    Pair<Integer, Integer> slope = getSlope(p1, p2);
                    slopeCount.merge(slope, 1, Integer::sum);
                    maxPoints = Math.max(maxPoints, slopeCount.get(slope));
                }
            }

            ans = Math.max(ans, samePoints + maxPoints);
        }

        return ans;
    }

    private Pair<Integer, Integer> getSlope(int[] p, int[] q) {
        int dx = p[0] - q[0];
        int dy = p[1] - q[1];

        // Handle vertical line (dx = 0)
        if (dx == 0) {
            return new Pair<>(0, 1); // Represent vertical as (0,1)
        }
        // Handle horizontal line (dy = 0)
        if (dy == 0) {
            return new Pair<>(1, 0); // Represent horizontal as (1,0)
        }

        // Reduce fraction using GCD
        int g = gcd(Math.abs(dx), Math.abs(dy));
        dx /= g;
        dy /= g;

        // Ensure consistent sign: make sure dx is positive, or if dx is negative, flip signs
        if (dx < 0) {
            dx = -dx;
            dy = -dy;
        }

        return new Pair<>(dx, dy);
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}