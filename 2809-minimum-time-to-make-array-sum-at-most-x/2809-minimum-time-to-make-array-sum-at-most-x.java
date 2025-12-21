class Solution {
        public int minimumTime(List<Integer> A, List<Integer> B, int x) {
        int n = A.size(), sa = 0, sb = 0, dp[] = new int[n + 1];
        List<List<Integer>> BA = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int a = A.get(i), b = B.get(i);
            BA.add(Arrays.asList(b, a));
            sa += a;
            sb += b;
        }
        Collections.sort(BA, (o1, o2) -> Integer.compare(o1.get(0), o2.get(0)));
        for (int j = 0; j < n; ++j)
            for (int i = j + 1; i > 0; --i)
                dp[i] = Math.max(dp[i], dp[i - 1] + i * BA.get(j).get(0) + BA.get(j).get(1));
        for (int i = 0; i <= n; ++i)
            if (sb * i + sa - dp[i] <= x)
                return i;
        return -1;
    }
}