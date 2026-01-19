class Solution {
    boolean checkc(int a[][],int x){
        boolean b[]=new boolean[a.length+1];
        for(int i=0;i<a.length;i++)
            if(b[a[x][i]])
                return false;
            else
                b[a[x][i]]=true;
        return true;
    }
    boolean checkr(int a[][],int x){
        boolean b[]=new boolean[a.length+1];
        for(int i=0;i<a.length;i++)
            if(b[a[i][x]])
                return false;
            else
                b[a[i][x]]=true;
        return true;
    }
    public boolean checkValid(int[][] matrix) {
        for(int i=0;i<matrix.length;i++)
            if(!checkr(matrix,i)||!checkc(matrix,i))
                return false;
        return true;
    }
}