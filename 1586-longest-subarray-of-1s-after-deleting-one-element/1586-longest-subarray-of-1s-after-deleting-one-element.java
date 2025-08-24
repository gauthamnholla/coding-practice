class Solution {
    public int longestSubarray(int[] nums) {
        // Sliding window approach
        int left = 0;      // Left pointer of the window
        int zeros = 0;     // Number of zeros inside the current window
        int res = 0;       // Result: longest subarray length with at most one zero removed

        // Expand the window with the right pointer
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) zeros++;  // Count zeros in the window

            // If there are more than 1 zero, shrink from the left
            while (zeros > 1) {
                if (nums[left] == 0) zeros--;  // Remove a zero when moving left pointer
                left++;
            }

            // Update result:
            // (right - left) gives the length after deleting *one* zero
            res = Math.max(res, right - left);
        }

        return res;
    }
}
