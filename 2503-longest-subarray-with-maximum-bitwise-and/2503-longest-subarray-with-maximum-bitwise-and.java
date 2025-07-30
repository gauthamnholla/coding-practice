public class Solution {
    public int longestSubarray(int[] nums) {
        // Step 1: Find the maximum value in the array
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > max) max = num;
        }
        
        // Step 2: Find the length of the longest subarray where every element equals the maximum value
        int maxLen = 0;    // To store the result
        int currLen = 0;   // To store the current streak length
        for (int num : nums) {
            if (num == max) {
                currLen++;                // Increment current streak if num equals max
                if (currLen > maxLen) {
                    maxLen = currLen;     // Update answer if current streak is longer
                }
            } else {
                currLen = 0;              // Reset streak if num is not max
            }
        }
        
        // Step 3: Return the result
        return maxLen;
    }
}
