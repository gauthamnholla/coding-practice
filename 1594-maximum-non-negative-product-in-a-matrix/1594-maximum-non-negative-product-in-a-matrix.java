class Solution {
    private long mx = -1;
    
    private void dfs(int[][] grid, int i, int j, long prod, int n, int m) {
        if(i>=n || j>=m) return;
        if(grid[i][j]==0) {
            mx = Math.max(mx, 0);
            return;
        }
        
        prod *= grid[i][j];
        
        if(i==n-1 && j==m-1) {
            mx = Math.max(mx, prod);
            return;
        }
        
        dfs(grid, i+1, j, prod, n, m);
        dfs(grid, i, j+1, prod, n, m);
    }

    public int maxProductPath(int[][] grid) {
        int n=grid.length, m=grid[0].length;

        dfs(grid,0,0,1,n,m);
        
        long mod = (long)1e9 + 7;
        return (mx < 0 ? -1 : (int)(mx % mod));
    }
}