class Solution {
    public double findMaxAverage(int[] nums, int k) {
        double sum = 0;

        // Compute sum of the first window
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        double maxSum = sum; // Track the maximum window sum

        // Slide the window across the array
        for (int i = k; i < nums.length; i++) {
            sum = sum - nums[i - k] + nums[i]; // Remove old value, add new
            maxSum = Math.max(maxSum, sum);    // Update max
        }

        return maxSum / k; // Convert max sum to average
    }
}
