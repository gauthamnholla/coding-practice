class Solution {
    private final Random random = new Random(); // Random number generator
    private final boolean useBlocked;           // Strategy flag
    private final int n;                        // Range size
    private final Set<Integer> blocked;         // Blacklisted numbers
    private final List<Integer> allowed;        // Allowed numbers (if small list)

    public Solution(int n, int[] blacklist) {
        this.n = n;
        int blSize = blacklist.length;
        useBlocked = (blSize * 2 <= n); // Use blacklist if small, else precompute allowed list
        blocked = new HashSet<>(blSize);
        allowed = new ArrayList<>();

        // Add blacklisted numbers to set
        for (int val : blacklist) {
            blocked.add(val);
        }

        // If blacklist is large, precompute all allowed numbers
        if (!useBlocked) {
            for (int val = 0; val < n; ++val) {
                if (!blocked.contains(val)) {
                    allowed.add(val);
                }
            }
        }
    }

    public int pick() {
        if (useBlocked) {
            // Randomly pick until finding a non-blacklisted number
            int val = random.nextInt(0, n);
            while (blocked.contains(val)) {
                val = random.nextInt(0, n);
            }
            return val;
        } else {
            // Randomly select from precomputed allowed numbers
            int idx = random.nextInt(0, allowed.size());
            return allowed.get(idx);
        }
    }
}

/**
 * Example usage:
 * Solution obj = new Solution(n, blacklist);
 * int param_1 = obj.pick();
 */
