public class Solution {
    public int findLHS(int[] nums) {
        Arrays.sort(nums); // Sort the array
        int j = 0;         // Left pointer
        int maxLength = 0; // Track longest subsequence length

        // Use two pointers to find valid ranges
        for (int i = 0; i < nums.length; i++) {
            // Move left pointer until difference ≤ 1
            while (nums[i] - nums[j] > 1) {
                j++;
            }
            // If difference is exactly 1, update max length
            if (nums[i] - nums[j] == 1) {
                maxLength = Math.max(maxLength, i - j + 1);
            }
        }

        return maxLength; // Return longest harmonious subsequence length
    }
}
