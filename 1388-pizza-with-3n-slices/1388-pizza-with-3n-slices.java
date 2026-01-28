class Solution {
        public final int maxSizeSlices(final int[] slices) {
        final Map<String, Integer> dp = new HashMap<>();
        return maxSizeSlices(slices, 0, slices.length - 1, slices.length / 3, 1, dp);
    }

    private final int maxSizeSlices(final int[] slices, final int start, final int end,
                                    final int n, final int cycle, final Map<String, Integer> dp) {
        final String serialized = start + "$" + end + "$" + n + "$" + cycle;
        if (dp.containsKey(serialized)) {
            return dp.get(serialized);
        }
        if (n == 1) {
            int max = Integer.MIN_VALUE;
            for (int i = start; i <= end; i++) {
                max = Math.max(max, slices[i]);
            }
            dp.put(serialized, max);
            return dp.get(serialized);
        }
        if (end - start + 1 < n * 2 - 1) {
            dp.put(serialized, Integer.MIN_VALUE);
            return dp.get(serialized);
        }
        final int res = Math.max(maxSizeSlices(slices, start + cycle, end - 2, n - 1, 0, dp) + slices[end],
                                maxSizeSlices(slices, start, end - 1, n, 0, dp));
        dp.put(serialized, res);
        return dp.get(serialized);
    }
}