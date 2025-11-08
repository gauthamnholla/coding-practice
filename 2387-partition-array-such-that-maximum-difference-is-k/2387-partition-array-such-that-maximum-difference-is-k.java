class Solution {
    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums); // Sort the array
        int ans = 1;       // Start with one group
        int min = nums[0]; // Smallest value in current group

        for (int i = 1; i < nums.length; i++) {
            // If difference is more than k, start a new group
            if (nums[i] - min > k) {
                ans++;
                min = nums[i];
            }
        }

        return ans; // Total groups formed
    }
}
