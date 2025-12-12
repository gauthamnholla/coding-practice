class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int leftBoundary = -1, rightBoundary = -1;
        
        // Phase 1: Find initial boundaries of disorder
        // Scan left to right to find leftmost out-of-place element
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i-1]) {
                leftBoundary = i - 1;
                break;
            }
        }
        
        // If no disorder found, array is already sorted
        if (leftBoundary == -1) {
            return 0;
        }
        
        // Scan right to left to find rightmost out-of-place element
        for (int i = n-2; i >= 0; i--) {
            if (nums[i] > nums[i+1]) {
                rightBoundary = i + 1;
                break;
            }
        }
        
        // Phase 2: Expand boundaries based on min/max of unsorted region
        // Find min and max values in the initially identified unsorted portion
        int unsortedMin = Integer.MAX_VALUE;
        int unsortedMax = Integer.MIN_VALUE;
        
        for (int i = leftBoundary; i <= rightBoundary; i++) {
            unsortedMin = Math.min(unsortedMin, nums[i]);
            unsortedMax = Math.max(unsortedMax, nums[i]);
        }
        
        // Expand left boundary: include elements greater than minimum
        while (leftBoundary > 0 && nums[leftBoundary-1] > unsortedMin) {
            leftBoundary--;
        }
        
        // Expand right boundary: include elements smaller than maximum
        while (rightBoundary < n-1 && nums[rightBoundary+1] < unsortedMax) {
            rightBoundary++;
        }
        
        return rightBoundary - leftBoundary + 1;
    }
}