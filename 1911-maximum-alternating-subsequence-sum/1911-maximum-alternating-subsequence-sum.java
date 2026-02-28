class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        //Bottom Up Approach..
        long [][]dp = new long[n][2];
        //here at every index we are storing the max sum for even and odd length combo
        //0 - even 
        //1 -odd

        dp[0][0]=0;//1st element is at 0 i.e this is at the odd place
        dp[0][1]=nums[0];//1st element is at 0 i.e this is at the odd place
        
        for(int i=1;i<n;i++){
            int sq= nums[i];
            dp[i][0]= Math.max(dp[i-1][1]-sq , dp[i-1][0]); //for even pos
            //
            dp[i][1]= Math.max(dp[i-1][0]+sq , dp[i-1][1]); //for odd pos
        }
        return Math.max(dp[n-1][0],dp[n-1][1]);
    }
}