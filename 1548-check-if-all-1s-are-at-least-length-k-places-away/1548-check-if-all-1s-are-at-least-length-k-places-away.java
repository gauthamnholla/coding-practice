class Solution {
    public boolean kLengthApart(int[] nums, int k) {
        int lastIndex = -1; // Stores the last position where we saw a 1

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] == 1) {
                // Check gap between current and previous 1
                if (lastIndex != -1 && i - lastIndex - 1 < k) {
                    return false; // Gap is too small
                }
                lastIndex = i; // Update last seen index of 1
            }
        }

        return true; // All gaps satisfy the condition
    }
}
