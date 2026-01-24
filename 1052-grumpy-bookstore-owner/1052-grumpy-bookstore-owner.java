class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int k) {
       int satisfied=0;
       int notsatisfied=0;
       for(int i=0;i<grumpy.length;i++){
        if(grumpy[i]==0) satisfied+=customers[i];
       }
       for(int i=0;i<k;i++){
        if(grumpy[i]==1) notsatisfied+=customers[i];
       }
       int maxnotsatisfied=notsatisfied;
       for(int i=k;i<customers.length;i++){
        if(grumpy[i]==1) notsatisfied+=customers[i];
        if(grumpy[i-k]==1) notsatisfied-=customers[i-k];
        maxnotsatisfied=Math.max(maxnotsatisfied,notsatisfied);
       }


        return maxnotsatisfied+satisfied;
   }
}