class Solution {
    private boolean possible(long t, int[] pts, int M) {
        long mv = 0, ext = 0, bon = 0;
        for (int p : pts) {
            long req = (t + p - 1L) / p;
            if (ext >= req) { ext = 0; bon++; }
            else { long d = req - ext; mv += 2 * d - 1 + bon; ext = d - 1; bon = 0; }
            if (mv > M) return false;
        }
        return true;
    }
    public long maxScore(int[] pts, int M) {
        if (M < pts.length) return 0;
        long lo = 1, hi = (long)1e18, ans = 0;
        while (lo <= hi) {
            long mid = lo + (hi - lo) / 2;
            if (possible(mid, pts, M)) { ans = mid; lo = mid + 1; }
            else hi = mid - 1;
        }
        return ans;
    }
}