class Solution {
    public int findMin(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        // Initialize min with the first element (arbitrary safe start)
        int min = nums[0];

        while (start <= end) {
            int mid = start + (end - start) / 2;
            
            // Comparison with the last element determines which side is sorted
            if (nums[mid] <= nums[nums.length - 1]) {
                // Right side is sorted. 
                // The current mid could be the minimum, or the min is to the left.
                min = Math.min(min, nums[mid]);
                end = mid - 1;
            } else if (nums[mid] >= nums[0]) {
                // Left side is sorted, but values are high (part of the first ascending sequence).
                // The minimum MUST be in the unsorted right part.
                start = mid + 1;
            }
        }
        return min;
    }
}