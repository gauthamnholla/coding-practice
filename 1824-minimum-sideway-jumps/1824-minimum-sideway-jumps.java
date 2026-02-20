class Solution {
    public int minSideJumps(int[] arr) {
        int dp [][] = new int[arr.length][3];
        for(int [] outer: dp){
            Arrays.fill(outer,Integer.MAX_VALUE);
        }
        return helper(0,arr,dp,1);
    }

    private static int helper(int idx,int [] arr,int [] [] dp,int lane){
        if(idx == arr.length-1)return 0;

        if(dp[idx][lane] != Integer.MAX_VALUE)return dp[idx][lane];

        int min = Integer.MAX_VALUE;
        if(lane+1 != arr[idx+1]){
            
            min = helper(idx+1,arr,dp,lane);
        }
        else{
            for(int i = 0;i<=2;i++){
                if(lane == i || i+1 == arr[idx] )continue;
                dp[idx][i] =  helper(idx,arr,dp,i); 
                min = Math.min(1+dp[idx][i],min);    
            }
        }
        return dp[idx][lane] = min;
    }
}