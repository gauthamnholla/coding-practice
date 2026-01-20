class Solution {
    int getOperationCount(int[][] grid, int y, int notY){
    int ans = 0, n = grid.length;
    for(int i = 0; i < n; ++i){
        for(int j = 0; j < n; ++j){
            if((i <= n/2 && (i == j || i + j == n - 1)) || (i > n/2 && j == n/2)){
                if(y != grid[i][j]) ans++;
            }else{
                if(notY != grid[i][j]) ans++;
            }
        }
    }
    return ans;
}
    
public int minimumOperationsToWriteY(int[][] grid) {
    int ans = Integer.MAX_VALUE;
    for(int i = 0; i < 3; ++i){
        for(int j = 0; j < 3; ++j){
            if(i != j) ans = Math.min(ans, getOperationCount(grid, i,j));
        }
    }
    return ans;
}
}