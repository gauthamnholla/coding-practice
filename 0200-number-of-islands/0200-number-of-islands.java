class Solution {
    public int numIslands(char[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        int count=0;
        for(int row=0; row<m; row++)
        {
            for(int col=0; col<n; col++)
            {
                if(grid[row][col]=='1')   
                {
                    helper(row, col, grid, m, n);
                    count++;  //Increase count after we have checked for the whole island.
                }
            }
        }
        return count;
    }
    public void helper(int row, int col, char[][] grid, int m, int n)
    { 
        if(col+1<n && grid[row][col+1]=='1')   //Check right
        {
            grid[row][col]='0';       //Mark the current position '0'
            helper(row, col+1, grid, m, n);   //Move right
        }
        if(row+1<m && grid[row+1][col]=='1') //check down
        {
            grid[row][col]='0';      //Mark the current position '0'
            helper(row+1, col, grid, m, n); //Move down
        }
        if(col-1>=0 && grid[row][col-1]=='1') //Check left
        {
            grid[row][col]='0';       //Mark the current position '0'
            helper(row, col-1, grid, m, n);   //Move left
        }
        if(row-1>=0 && grid[row-1][col]=='1') //Check up
        {
            grid[row][col]='0';  //Mark the current position '0'
            helper(row-1, col, grid, m, n);   //move up
        }
        else
        {
            grid[row][col]='0';
            return;   
        }
    }
}