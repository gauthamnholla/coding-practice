class Solution {
    public int removeElement(int[] nums, int val) {
        int k = 0; // Pointer for position to place valid elements

        // Traverse all elements
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {     // Keep elements not equal to val
                nums[k] = nums[i];    // Move valid element forward
                k++;
            }
        }

        return k; // Return count of remaining elements
    }
}
