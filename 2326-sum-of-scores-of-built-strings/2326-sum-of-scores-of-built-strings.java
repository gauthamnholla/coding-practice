class Solution {
    public long sumScores(String s) {
        int n = s.length();
        int[] Z = new int[n];
        Z[0] = n;
        int l = 0, r = 0;
        for (int i = 1; i < n; i++) {
            if (i <= r) {
                Z[i] = Math.min(r - i + 1, Z[i - l]);
            } else {
                Z[i] = 0;
            }
            while (i + Z[i] < n && s.charAt(Z[i]) == s.charAt(i + Z[i])) {
                Z[i]++;
            }
            if (i + Z[i] - 1 > r) {
                l = i;
                r = i + Z[i] - 1;
            }
        }

        long sum = 0;
        for (int x : Z) sum += x;
        return sum;
    }
}
