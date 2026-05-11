class Solution {
    private long[][] dp;

    private long f(int[] A, int[] B, int i, int curr) {
        if (i >= A.length) return 0;
        if (dp[i][curr] != -1) return dp[i][curr];

        long ans = 0;
        if (curr == 1) {
            ans = Math.max(A[i] + f(A, B, i + 1, curr), f(A, B, i + 1, 1 - curr));
        } else {
            ans = Math.max(B[i] + f(A, B, i + 1, curr), f(A, B, i + 1, 1 - curr));
        }

        return dp[i][curr] = ans;
    }

    public long maxEnergyBoost(int[] energyDrinkA, int[] energyDrinkB) {
        int n = energyDrinkA.length;
        dp = new long[n][2];
        for (long[] row : dp) {
            Arrays.fill(row, -1);
        }
        return Math.max(f(energyDrinkA, energyDrinkB, 0, 1), f(energyDrinkA, energyDrinkB, 0, 0));
    }
}