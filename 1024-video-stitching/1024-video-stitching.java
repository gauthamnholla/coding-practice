class Solution {
    public int videoStitching(int[][] clips, int time) {
        int[] maxReach = new int[101]; // Constraint: 0 <= starti <= endi <= 100

        // Populate maxReach array
        for (int[] clip : clips) {
            int start = clip[0];
            int end = clip[1];
            maxReach[start] = Math.max(maxReach[start], end);
        }

        int minClips = 0;
        int currentEnd = 0;
        int furthestReach = 0;

        for (int i = 0; i <= time; i++) {
            if (i > furthestReach) {
                return -1; // Impossible to cover the entire time
            }

            if (i > currentEnd) {
                minClips++;
                currentEnd = furthestReach;
            }

            furthestReach = Math.max(furthestReach, maxReach[i]);
        }

        return minClips;
    }
}