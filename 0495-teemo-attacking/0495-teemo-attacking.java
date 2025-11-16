class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int count = 0;

        // Calculate overlapping or non-overlapping poison intervals
        for (int i = 0; i < timeSeries.length - 1; i++) {
            // Add only the effective time (overlap reduces duration)
            count += Math.min(duration, timeSeries[i + 1] - timeSeries[i]);
        }

        // Add full duration for the last attack
        count += duration;

        return count;
    }
}
