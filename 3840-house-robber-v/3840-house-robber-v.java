class Solution {
    public long rob(int[] nums, int[] colors) {
        int n = nums.length;

        long dp0 = 0, dp1 = nums[0];
        for (int i = 1; i < n; i++) {
            long new0, new1;
            if (colors[i] == colors[i-1]) {
                new1 = dp0 + nums[i];
                new0 = Math.max(dp0, dp1);
            } else {
                new0 = Math.max(dp1, dp0);
                new1 = Math.max(dp0, dp1) + nums[i];
            }
            dp0 = new0; dp1 = new1;
        }
        return Math.max(dp0, dp1);
    }
}