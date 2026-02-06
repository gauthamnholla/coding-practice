class Solution {
        public long sumDigitDifferences(int[] A) {
        int n = A.length, m = String.valueOf(A[0]).length();
        int[][] count = new int[m][10];
        long res = 1L * n * (n - 1) / 2 * m;
        for (int a : A) {
            for (int i = 0; i < m && a > 0; i++) {
                res -= count[i][a % 10]++;
                a /= 10;
            }
        }
        return res;
    }
}