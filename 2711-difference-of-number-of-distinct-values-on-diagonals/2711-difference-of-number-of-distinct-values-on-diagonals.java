class Solution {
    public int[][] differenceOfDistinctValues(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        
        int[][] ans = new int[m][n];
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                
                //using set for determining distinct integers

                Set<Integer> topLeft = new HashSet(); 

                //traversing topLeft diagonal, 
                for(int r = i-1, c = j-1; r >= 0 && c >= 0; r--, c--){
                    topLeft.add(grid[r][c]);
                }
                
                Set<Integer> bottomRight = new HashSet();

                // traversing bottomRight diagonal
                for(int r = i+1, c = j+1; r < m && c < n; r++, c++){
                    bottomRight.add(grid[r][c]);
                }
                
                ans[i][j] = Math.abs(bottomRight.size() - topLeft.size());
            }
        }
        
        return ans;
    }
}