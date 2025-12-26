class Solution {
    private void dfs(int[][] grid, int i, int j, Set<int[]> isl) {
        if(i < 0 || j < 0 || i >= grid.length || j >= grid.length || grid[i][j] == 0)
            return;
        for(int[] cell : isl) {
            if(cell[0] == i && cell[1] == j) return;
        }
        isl.add(new int[]{i,j});
        grid[i][j] = 0;
        dfs(grid, i+1, j, isl);
        dfs(grid, i-1, j, isl);
        dfs(grid, i, j+1, isl);
        dfs(grid, i, j-1, isl);
    }
    
    public int shortestBridge(int[][] grid) {
        Set<int[]> isl1 = new HashSet<>();
        Set<int[]> isl2 = new HashSet<>();
        boolean first = true;
        for(int i = 0; i < grid.length; i++)
            for(int j = 0; j < grid.length; j++)
                if(grid[i][j] == 1) {
                    if(first) { dfs(grid,i,j,isl1); first = false; }
                    else dfs(grid,i,j,isl2);
                }
        
        int ans = Integer.MAX_VALUE;
        for(int[] c1 : isl1) {
            for(int[] c2 : isl2) {
                ans = Math.min(ans, Math.abs(c1[0]-c2[0]) + Math.abs(c1[1]-c2[1]) - 1);
            }
        }
        return ans;
    }
}