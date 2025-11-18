class Solution {
    public int projectionArea(int[][] grid) {
        int[] front=new int[grid.length];
        int base=0,back=0,total=0;
        for(int i=0;i<grid.length;i++)
        {
            int temp=0;
            for(int j=0;j<grid.length;j++)
            {
                if(grid[i][j]!=0)
                {
                    //count all non zeros
                    base+=1;
                    //to get max of row
                    if(grid[i][j]>temp)
                    {
                        temp=grid[i][j];
                    }
                    //store all the max value of columns
                    if(grid[i][j]>front[j])
                    {
                        front[j]=grid[i][j];
                    }
                }
            }
            total+=temp;
        }
        for(int i=0;i<front.length;i++)
        {
            back+=front[i];
        }
        return total+back+base;
    }
}