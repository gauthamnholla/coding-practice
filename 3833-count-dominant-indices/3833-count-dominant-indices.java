class Solution {
    public int dominantIndices(int[] a) {
        int n = a.length;
        long sum = 0;
        int cnt = 0;

        for (int i = n - 1; i > 0; i--) {
            sum += a[i];
            if (a[i - 1] > sum / (n - i)) {
                cnt++;
            }
        }
        return cnt;
    }
}