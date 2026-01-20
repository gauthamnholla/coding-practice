class Solution {
    int check1(int y){
        int c=y<=0?1:0;
        while(y!=0){
            c++;
            y/=10;
        }
        return c;
    }
    int check(int g[][], int i){
        int max=0;
        for(int j=0;j<g.length;j++)
            max=Math.max(max,check1(g[j][i]));
        return max;
    }
    public int[] findColumnWidth(int[][] grid) {
        int a[]=new int[grid[0].length];
        for(int i=0;i<grid[0].length;i++)
            a[i]=check(grid,i);
        return a;
    }
}