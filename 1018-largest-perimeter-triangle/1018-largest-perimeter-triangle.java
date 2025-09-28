class Solution {
    public int largestPerimeter(int[] nums) {
        // Step 1 - Sort the array in ascending order
        Arrays.sort(nums);

        // Step 2 - Traverse from the largest element (potential largest side)
        for (int i = nums.length - 1; i >= 2; i--) {
            
            // Step 3 - Check triangle inequality:
            // For a triangle with sides a ≤ b ≤ c:
            // It is valid if (a + b > c)
            if (nums[i - 1] + nums[i - 2] > nums[i]) {
                
                // Step 4 - If valid, return perimeter (sum of 3 sides)
                return nums[i] + nums[i - 1] + nums[i - 2];
            }
        }

        // Step 5 - If no valid triangle can be formed, return 0
        return 0;
    }
}
