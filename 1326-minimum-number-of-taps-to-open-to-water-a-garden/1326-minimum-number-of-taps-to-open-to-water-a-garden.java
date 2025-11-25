class Solution {
    public int minTaps(int n, int[] ranges) {
        // maxReach[i] = farthest right we can reach by opening a tap whose left bound is exactly i
        int[] maxReach = new int[n + 1];

        // build maxReach
        for (int i = 0; i <= n; i++) {
            int left = Math.max(0, i - ranges[i]);
            int right = Math.min(n, i + ranges[i]);
            maxReach[left] = Math.max(maxReach[left], right);
        }

        int taps = 0;
        int curEnd = 0;   // current covered end by opened taps
        int nextEnd = 0;  // farthest we can reach while scanning positions <= i

        for (int i = 0; i <= n; i++) {
            nextEnd = Math.max(nextEnd, maxReach[i]);

            // when we've scanned up to the current coverage boundary,
            // we must open another tap to extend coverage
            if (i == curEnd) {
                // if nextEnd didn't move forward, it's impossible to cover position i
                if (nextEnd == curEnd) return -1;

                taps++;
                curEnd = nextEnd;
                if (curEnd >= n) return taps; // covered whole garden
            }
        }

        return -1; // should not reach here ordinarily
    }
}
