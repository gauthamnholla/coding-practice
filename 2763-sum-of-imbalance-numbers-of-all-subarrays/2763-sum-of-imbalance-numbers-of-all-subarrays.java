class Solution {
        public int sumImbalanceNumbers(int[] A) {
        int res = 0, n = A.length;
        for (int i = 0; i < n; ++i) {
            Set<Integer> s = new HashSet<>();
            s.add(A[i]);
            int cur = 0;
            for (int j = i + 1; j < n; ++j) {
                if (!s.contains(A[j])) {
                    int d = 1;
                    if (s.contains(A[j] - 1)) d--;
                    if (s.contains(A[j] + 1)) d--;
                    cur += d;
                    s.add(A[j]);
                }
                res += cur;
            }
        }
        return res;
    }
}