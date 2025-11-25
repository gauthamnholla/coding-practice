class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long K = (long)k1 + (long)k2;

        long[] d = new long[n];
        long sumD = 0;
        long maxD = 0;
        for (int i = 0; i < n; i++) {
            d[i] = Math.abs((long)nums1[i] - (long)nums2[i]);
            sumD += d[i];
            if (d[i] > maxD) maxD = d[i];
        }

        // If we have enough operations to reduce all diffs to zero:
        if (K >= sumD) return 0L;

        // Binary search threshold t in [0, maxD]
        long lo = 0, hi = maxD;
        while (lo < hi) {
            long mid = (lo + hi) >>> 1;
            long need = 0;
            for (int i = 0; i < n; i++) {
                if (d[i] > mid) need += d[i] - mid;
                if (need > K) break; // early exit
            }
            if (need <= K) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        long t = lo;

        // Compute how many decrements were used to bring all d[i] > t down to t
        long used = 0;
        long cntGreater = 0;
        for (int i = 0; i < n; i++) {
            if (d[i] > t) {
                used += d[i] - t;
                cntGreater++;
            }
        }
        long r = K - used; // remaining decrements to apply to some elements currently at t

        // Compute final sum of squares:
        // - for d[i] <= t: contribute d[i]^2
        // - for d[i] > t: initially contribute t^2 (we lowered them), then we will reduce r of those by 1 to (t-1)
        long res = 0L;
        for (int i = 0; i < n; i++) {
            if (d[i] <= t) {
                res += d[i] * d[i];
            } else {
                res += t * t;
            }
        }

        // apply remaining r decrements (each reduces one t -> t-1), each reduces sum by (2*t - 1)
        // r cannot exceed number of elements with value strictly greater than t originally? 
        // It can be applied to any element that currently equals t (those originally > t plus possibly those exactly = t),
        // but the binary search ensures r < number of elements >= t (otherwise t would be smaller).
        if (r > 0) {
            long reducePer = 2L * t - 1L;
            res -= r * reducePer;
        }

        return res;
    }
}
