class Solution {
    int r, c;
    int[][] dp;
    List<Integer> colMin;

    public int solve(int row, int mask, List<List<Integer>> cost) {
        if (row >= r) {
            int rem = 0;
            for (int j = 0; j < c; ++j) {
                // take the remaining ones which were not in the mask
                if ((mask & (1 << j)) == 0) rem += colMin.get(j);
            }
            return rem;
        }
        if (dp[row][mask] != -1) return dp[row][mask];
        int ans = Integer.MAX_VALUE;

        for (int j = 0; j < c; ++j)
            ans = Math.min(ans, cost.get(row).get(j) + solve(row + 1, mask | (1 << j), cost));
        return dp[row][mask] = ans;
    }

    public int connectTwoGroups(List<List<Integer>> cost) {
        r = cost.size();
        c = cost.get(0).size();
        dp = new int[12][1 << 12];
        for (int[] row : dp) Arrays.fill(row, -1);
        colMin = Arrays.asList(new Integer[c]);
        for (int i = 0; i < c; ++i) colMin.set(i, Integer.MAX_VALUE);

        for (int i = 0; i < r; ++i)
            for (int j = 0; j < c; ++j)
                colMin.set(j, Math.min(colMin.get(j), cost.get(i).get(j)));

        return solve(0, 0, cost);
    }
}