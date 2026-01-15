class Solution {
        public long numGoodSubarrays(int[] A, int k) {
        Map<Integer, Long> cnt = new HashMap<>();
        cnt.put(0, 1L);
        int pre = 0, n = A.length;
        long res = 0;
        for (int a : A) {
            pre = (pre + a) % k;
            res += cnt.getOrDefault(pre, 0L);
            cnt.put(pre, cnt.getOrDefault(pre, 0L) + 1L);
        }
        for (int i = 0; i < n; ) {
            int j = i;
            while (j < n && A[j] == A[i]) ++j;
            int l = j - i;
            for (int ll = 1; ll < l; ++ll)
                if ((1L * ll * A[i]) % k == 0)
                    res -= (l - ll);
            i = j;
        }
        return res;
    }
}