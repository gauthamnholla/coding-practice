class Solution {
    public List<Integer> zigzagTraversal(int[][] grid) {
        List<Integer> l=new ArrayList<>();
        int r=grid.length;
        int c=grid[0].length;
        int k=0;
        if(c%2==0){
            k=c-1;
        }
        else{
            k=c-2;
        }
        int limit=(r*c+1)/2;
        int dir=0;
        int cnt=0;
        while(l.size()<limit){
            if(dir==0){
                for(int i=0;i<c;i+=2){
                    l.add(grid[cnt][i]);
                }
                cnt++;
                dir=1;
            }
            else{
                for(int j=k;j>=0;j-=2){
                    l.add(grid[cnt][j]);
                }
                cnt++;
                dir=0;
            }           
        }
        return l;
    }
}