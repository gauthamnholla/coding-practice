class Solution {
    public int numSubseq(int[] nums, int target) {
        int mod = 1000000007, n = nums.length;
        Arrays.sort(nums); // Sort array for two-pointer logic

        // Precompute powers of 2 (for subsequence count)
        int[] power = new int[n];
        power[0] = 1;
        for (int i = 1; i < n; i++) {
            power[i] = (power[i - 1] * 2) % mod;
        }

        int left = 0, right = n - 1, result = 0;

        // Two-pointer approach
        while (left <= right) {
            // If sum within target, count all subsequences between left and right
            if (nums[left] + nums[right] <= target) {
                result = (result + power[right - left]) % mod;
                left++;
            } else {
                right--; // Otherwise, move right pointer left
            }
        }

        return result; // Return total valid subsequences
    }
}

