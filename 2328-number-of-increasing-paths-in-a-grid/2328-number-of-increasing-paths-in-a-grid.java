import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;
    public int countPaths(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int N = m * n;
        // Prepare list of cell indices and sort by value descending
        Integer[] idx = new Integer[N];
        for (int i = 0; i < N; i++) idx[i] = i;
        Arrays.sort(idx, (a, b) -> Integer.compare(grid[a / n][a % n], grid[b / n][b % n]) * -1);

        long[] dp = new long[N]; // dp[c] = number of strictly increasing paths starting at cell c
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        long ans = 0;
        for (int id : idx) {
            int r = id / n, c = id % n;
            long ways = 1; // path of length 1 (the cell itself)
            int val = grid[r][c];
            // accumulate dp of neighbors with strictly greater value
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                if (grid[nr][nc] > val) ways = (ways + dp[nr * n + nc]) % MOD;
            }
            dp[id] = ways;
            ans += ways;
            if (ans >= MOD) ans -= MOD;
        }
        return (int) ans;
    }
}
