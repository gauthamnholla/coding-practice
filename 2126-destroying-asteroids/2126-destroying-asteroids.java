class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        long m = mass;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        // Find min and max asteroid sizes
        for (int a : asteroids) {
            min = Math.min(min, a);
            max = Math.max(max, a);
        }

        // Frequency array to store asteroid counts
        int[] count = new int[max + 1];

        // Try to absorb what we can immediately and store others
        for (int a : asteroids) {
            if (a <= m) {
                m += a; // Absorb
            } else {
                count[a]++; // Store for later
            }
        }

        // Try to absorb remaining asteroids from smallest to largest
        for (int i = min; i <= max; i++) {
            if (count[i] > 0) {
                if (m >= i) {
                    m += (long) i * count[i]; // Absorb all of this size
                } else {
                    return false; // Can't absorb, fail
                }
            }
        }

        return true; // Survived all asteroids
    }
}