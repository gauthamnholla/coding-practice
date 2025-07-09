class Solution {
    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        int n = startTime.length;
        int maxFree = 0;
        int currentOccupied = 0;

        // Precompute occupied time for the first window
        for (int i = 0; i < k; i++) {
            currentOccupied += endTime[i] - startTime[i];
        }

        for (int i = k - 1; i < n; i++) {
            // Calculate window start and end
            int windowStart = (i == k - 1) ? 0 : endTime[i - k];
            int windowEnd = (i == n - 1) ? eventTime : startTime[i + 1];

            int freeTime = windowEnd - windowStart - currentOccupied;
            maxFree = Math.max(maxFree, freeTime);

            // Slide the window
            if (i + 1 < n) {
                currentOccupied -= endTime[i - k + 1] - startTime[i - k + 1];
                currentOccupied += endTime[i + 1] - startTime[i + 1];
            }
        }

        return maxFree;
    }
}
