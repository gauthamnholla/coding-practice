class Solution {
    public int maximumRows(int[][] mat, int cols) {
        
        int m=mat.length;
        int n=mat[0].length;
        
        int[] s=new int[n];
        
        return f(0,m,n,cols,mat,s);
    }
    
    public int f(int ind,int m,int n,int cols,int[][] mat,int[] s){
        
        if(cols==0){
            int count=0;
            for(int i=0; i<m; i++){
			//supposing that this row is valid
                boolean selected=true;
                for(int j=0; j<n; j++){
				//if any cell of this row violates the given two conditions, then the row is discarded
                    if(mat[i][j]==1 && s[j]!=1)
                        selected=false;
                }
                if(selected)
                    count+=1;
            }
            
            return count;
        }
        
        int ans=-1;
        for(int i=ind; i<n; i++){
            
            s[i]=1; //do
            ans=Math.max(ans,f(i+1,m,n,cols-1,mat,s));
            s[i]=0; //undo / backtrack
        }
        
        return ans;
    }
}