class Solution {
    double[][] memo;
    double[] prefix;
    int n;

    public double largestSumOfAverages(int[] nums, int k) {
        n = nums.length;
        memo = new double[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1.0);
        }

        // 0-based prefix
        prefix = new double[n];
        prefix[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }

        return dfs(0, k, nums);
    }

    private double dfs(int i, int k, int[] nums) {
        if (memo[i][k] >= 0) return memo[i][k];
        if (k == 1) { // only one group left: take avg of rest
            double sum = prefix[n - 1] - (i > 0 ? prefix[i - 1] : 0);
            return sum / (n - i);
        }

        double ans = 0;
        for (int j = i; j < n; j++) {
            double sum = prefix[j] - (i > 0 ? prefix[i - 1] : 0);
            double avg = sum / (j - i + 1);

            if (j + 1 < n) { // leave space for remaining groups
                ans = Math.max(ans, avg + dfs(j + 1, k - 1, nums));
            }
        }

        return memo[i][k] = ans;
    }
}