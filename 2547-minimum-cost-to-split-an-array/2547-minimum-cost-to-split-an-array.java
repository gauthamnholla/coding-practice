class Solution {
    public int minCost(int[] nums, int k) {
        int n = nums.length;
        int []dp = new int[n+1];
        for (int i = 0; i <= n; ++i) dp[i] = k + n + 1;
        dp[0] = 0;
        int []mp = new int[n+1];
        for (int j = 1; j <= n; ++j) {
            for (int i = 0; i <= n; ++i) mp[i] = 0;
            int rm = 0;
            for (int i = j; i >= 1; --i) {
                // update the removed number
                if (mp[nums[i-1]] == 0) rm++;
                else if (mp[nums[i-1]] == 1) rm--;
                mp[nums[i-1]]++;
                dp[j] = Math.min(dp[j], dp[i-1] + k + j - i + 1 - rm);
            }
        }
        return dp[n];
    }
}