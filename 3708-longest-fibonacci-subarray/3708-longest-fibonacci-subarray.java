class Solution {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        if (n <= 2) return n;
        int ans = 2, c = 2;
        for (int i = 2; i < n; i++) {
            if (nums[i] == nums[i-1] + nums[i-2]) c++;
            else c = 2;
            ans = Math.max(ans, c);
        }
        return ans;
    }
}