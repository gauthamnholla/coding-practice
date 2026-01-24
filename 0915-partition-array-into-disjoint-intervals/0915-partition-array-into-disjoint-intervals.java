class Solution {
     public int partitionDisjoint(int[] A) {
        int cmax=A[0]; //cmax:current maximum
        int nmax=A[0];//nmax: next maximum
        int ans=0;
        
        for(int i=1;i<A.length;i++){
            int val=A[i];
          nmax=Math.max(val,nmax); //next max for case if i exist in left Part
            
            if(val<cmax){
                ans=i;         //now left Part is till here
                cmax=nmax; //maximum of left array is nmax(as we are maintaining maximum for split Part)
            }
        }
        return ans+1; // we have to return length not idx so ans+1.
    }
}