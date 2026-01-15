class Solution {

    static class BIT {
        int n;
        long[] tree;

        BIT(int n) {
            this.n = n;
            tree = new long[n + 1];
        }

        void add(int i, long v) {
            for (; i <= n; i += i & -i) tree[i] += v;
        }

        long sum(int i) {
            long s = 0;
            for (; i > 0; i -= i & -i) s += tree[i];
            return s;
        }

        long range(int l, int r) {
            if (l > r) return 0;
            return sum(r) - sum(l - 1);
        }
    }

    public long[] countStableSubarrays(int[] nums, int[][] queries) {

        int n = nums.length;

        int[] segmentEnd = new int[n];
        int i = 0;

        while (i < n) {
            int j = i;
            while (j + 1 < n && nums[j] <= nums[j + 1]) j++;
            for (int k = i; k <= j; k++) segmentEnd[k] = j;
            i = j + 1;
        }

        long[] segmentLength = new long[n];
        for (int k = 0; k < n; k++) {
            segmentLength[k] = segmentEnd[k] - k + 1L;
        }

        int[][] pair = new int[n][2];
        for (int k = 0; k < n; k++) {
            pair[k][0] = segmentEnd[k];
            pair[k][1] = k;
        }
        Arrays.sort(pair, Comparator.comparingInt(a -> a[0]));

        int q = queries.length;
        long[] answer = new long[q];

        int[] left = new int[q];
        int[] right = new int[q];

        for (int k = 0; k < q; k++) {
            left[k] = queries[k][0];
            right[k] = queries[k][1];
        }

        Integer[] queryOrder = new Integer[q];
        for (int k = 0; k < q; k++) queryOrder[k] = k;
        Arrays.sort(queryOrder, Comparator.comparingInt(a -> right[a]));

        BIT bitCount = new BIT(n);
        BIT bitLength = new BIT(n);
        BIT bitIndexSum = new BIT(n);

        long[] prefIndexSum = new long[n + 1];
        for (int k = 1; k <= n; k++) prefIndexSum[k] = prefIndexSum[k - 1] + (k - 1);

        int ptr = 0;

        for (int qi : queryOrder) {
            int L = left[qi];
            int R = right[qi];

            while (ptr < n && pair[ptr][0] <= R) {
                int idx = pair[ptr][1];
                int pos = idx + 1;

                bitCount.add(pos, 1);
                bitLength.add(pos, segmentLength[idx]);
                bitIndexSum.add(pos, idx);

                ptr++;
            }

            int Lpos = L + 1;
            int Rpos = R + 1;

            long inCount = bitCount.range(Lpos, Rpos);
            long inLengthSum = bitLength.range(Lpos, Rpos);
            long inIndexSum = bitIndexSum.range(Lpos, Rpos);

            long total = R - L + 1L;
            long outCount = total - inCount;

            long fullIndexSum = prefIndexSum[R + 1] - prefIndexSum[L];
            long outIndexSum = fullIndexSum - inIndexSum;

            answer[qi] = inLengthSum + outCount * (R + 1L) - outIndexSum;
        }

        return answer;
    }
}