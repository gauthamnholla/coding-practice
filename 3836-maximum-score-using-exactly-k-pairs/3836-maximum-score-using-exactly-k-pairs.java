class Solution {
    public long maxScore(int[] nums1, int[] nums2, int k) {
        int n=nums1.length;
        int m=nums2.length;
        
        long[][][] dp=new long[n][m][k+1];
        for(long[][] i:dp){
            for(long[] j:i) Arrays.fill(j, Long.MIN_VALUE);
        }
        return helper(0, 0, nums1, nums2, k, dp);
    }

    public long helper(int i, int j, int[] nums1, int[] nums2, int k, long[][][] dp){
        if(i>=nums1.length || j>=nums2.length) {
            if(k==0)
                return 0;
            return Long.MIN_VALUE/2;
        }
        if(dp[i][j][k]!=Long.MIN_VALUE) return dp[i][j][k];
        long ans = Long.MIN_VALUE/2;
        ans = Math.max(ans, helper(i+1, j, nums1, nums2, k, dp));
        ans = Math.max(ans, helper(i, j+1, nums1, nums2, k, dp));
        ans = Math.max(ans, helper(i+1, j+1, nums1, nums2, k, dp));

        if(k>0){
            ans = Math.max(ans, (long)nums1[i]*nums2[j]+helper(i+1, j+1, nums1, nums2, k-1, dp));
        }

        return dp[i][j][k]=ans;
        
    }
}