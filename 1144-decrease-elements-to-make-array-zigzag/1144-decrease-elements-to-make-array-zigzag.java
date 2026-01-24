class Solution {
    public int movesToMakeZigzag(int[] nums) {
       int odd = 0, even =0;
       int n = nums.length ;

       for(int i=0 ; i< n ; i++){
        if((i-1>=0 && nums[i-1]<= nums[i]) || (i+1<n && nums[i+1]<= nums[i])){
            int min = Integer.MAX_VALUE ;

            if(i-1>=0){
                min = Math.min(min, nums[i-1]);
            }
            if(i+1<n){
            min = Math.min(min , nums[i+1]);
            }
           int cost = nums[i]-min+1;
            if(i %2 == 0){
                even += cost;
            }else{
                odd += cost;
            }
        }
       }

       return Math.min(even, odd); 
    }
}