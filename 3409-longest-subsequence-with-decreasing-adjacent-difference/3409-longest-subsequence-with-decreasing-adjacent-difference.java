class Solution {
    public int longestSubsequence(int[] nums) {
        int[][] dp=new int[301][301];
        int max=0;
        for(int i=0;i<=300;i++)dp[nums[0]][i]=1;
        for(int i=1;i<nums.length;i++){
            int g=nums[i];
            for(int j=0;j<=300;j++){
                int curr=0;
             if(g-j>0){
                curr=Math.max(dp[g-j][j]+1,curr);
             }
             if(g+j<=300){
               curr=Math.max(dp[g+j][j]+1,curr);
             }
             dp[g][j]=Math.max(curr,dp[g][j]);
            }
            for(int j=299;j>=0;j--){
               dp[g][j]=Math.max(dp[g][j],dp[g][j+1]);
               max=Math.max(max,dp[g][j]);
            }
            
        }
        return max;
        
    }

    
}