class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int ans = 1; // Length of current increasing sequence
        int max = 1; // Longest found so far

        for (int i = 1; i < nums.length; i++) {
            // If numbers are increasing, extend the current sequence
            if (nums[i - 1] < nums[i]) {
                ans++;
            } else {
                // Sequence broke → update max and reset
                max = Math.max(max, ans);
                ans = 1;
            }
        }

        // Compare final sequence length
        return Math.max(max, ans);
    }
}
