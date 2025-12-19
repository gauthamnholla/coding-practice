class Solution {
    public long maxSum(int[][] grid, int[] limits, int k) 
    {
     for(int i=0;i<grid.length;i++)
     {
        Arrays.sort(grid[i]);
     }
    List<Integer> list=new ArrayList<>();
     for(int i=0;i<grid.length;i++)
     {
        for(int j=grid[0].length-1;j>=(grid[0].length-limits[i]);j--)
        {
          list.add(grid[i][j]);
        }
     }
     Collections.sort(list);
     long sum=0;
     for(int i=list.size()-1;i>=list.size()-k;i--)
     {
        sum+=list.get(i);
     }
     return sum;   
    }
}