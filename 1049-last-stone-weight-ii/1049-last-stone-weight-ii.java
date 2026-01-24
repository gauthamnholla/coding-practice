class Solution {
    public int lastStoneWeightII(int[] stones) {
        int sumStWt = 0;
        for(int stone : stones){
            sumStWt += stone;
        }
        Integer[][] dp = new Integer[stones.length][sumStWt];
        return helper(stones, 0, 0, 0, dp);
    }
    
    private int helper(int[] stones, int index, int sumL, int sumR, Integer[][] dp) {
        if(index == stones.length){
            return Math.abs(sumL - sumR);
        }
        
        if(dp[index][sumL] != null) {
            return dp[index][sumL];
        }
        
        dp[index][sumL] = Math.min(helper(stones, index+1, sumL + stones[index], sumR, dp) , helper(stones, index+1, sumL, sumR + stones[index], dp));
        return dp[index][sumL];
    }
}