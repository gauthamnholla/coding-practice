class Solution {
    public int findKthPositive(int[] arr, int k) {
        int n = arr.length;
        // helper: missing count up to index i
        // missing(i) = arr[i] - (i + 1)

        // if k is larger than missing at last element, answer is after arr[n-1]
        int missingLast = arr[n-1] - n;
        if (k > missingLast) {
            return arr[n-1] + (k - missingLast);
        }

        // binary search first index lo with missing(lo) >= k
        int lo = 0, hi = n - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int missingMid = arr[mid] - (mid + 1);
            if (missingMid < k) lo = mid + 1;
            else hi = mid;
        }

        // lo is first index with missing(lo) >= k
        if (lo == 0) return k; // k-th missing is before the first element
        int missingPrev = arr[lo - 1] - lo; // missing(lo-1) = arr[lo-1] - (lo)
        return arr[lo - 1] + (k - missingPrev);
    }
}
