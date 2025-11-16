class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int count = 0;

        // Add poison time for each attack except last
        for (int i = 0; i < timeSeries.length - 1; i++) {
            // If next attack comes before poison ends, only add the gap.
            // Otherwise, add full duration.
            count += Math.min(duration, timeSeries[i + 1] - timeSeries[i]);
        }

        // Last attack always adds full duration
        count += duration;

        return count;
    }
}
