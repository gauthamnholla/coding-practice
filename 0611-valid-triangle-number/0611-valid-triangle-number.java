class Solution {
    public int triangleNumber(int[] nums) {
        // Step 1: Sort the array
        // This helps us use the two-pointer approach efficiently
        Arrays.sort(nums);
        int n = nums.length;
        int count = 0;

        // Step 2: Fix the largest side (nums[i]) of the triangle
        for (int i = n - 1; i >= 2; i--) {
            int left = 0;          // pointer at start
            int right = i - 1;     // pointer just before 'i'

            // Step 3: Use two pointers to find valid pairs for nums[i]
            while (left < right) {
                // If nums[left] + nums[right] > nums[i], then
                // all elements between left..right-1 will also form valid pairs with nums[right]
                if (nums[left] + nums[right] > nums[i]) {
                    count += right - left; // add all these pairs
                    right--;               // move 'right' leftward
                } else {
                    // If the sum is too small, increase left to try bigger side
                    left++;
                }
            }
        }

        // Step 4: Return total count of valid triangles
        return count;
    }
}
