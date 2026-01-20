class Solution {
    public int minimumOperations(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] count = new int[m][10];
        for (int column = 0; column < m; column++) {
            for (int value = 0; value < 10; value++) {
                for (int j = 0; j < n; j++) {
                    count[column][value] += grid[j][column] != value ? 1 : 0;
                }
            }
        }
        int[][] dp = new int[m + 1][10];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        Arrays.fill(dp[m], 0);
        for (int column = m - 1; column >= 0; column--) {
            for (int value = 0; value < 10; value++) {
                for (int i = 0; i < 10; i++) {
                    if (i != value) {
                        dp[column][value] = Math.min(dp[column][value], count[column][value] + dp[column + 1][i]);
                    }
                }
            }
        }
        return Arrays.stream(dp[0]).min().getAsInt();
    }
}