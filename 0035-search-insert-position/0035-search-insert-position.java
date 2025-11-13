class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        // Binary search to find target or its insert position
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid; // Found target
            } else if (nums[mid] > target) {
                right = mid - 1; // Search left half
            } else {
                left = mid + 1; // Search right half
            }
        }

        // If not found, left is the correct insert position
        return left;
    }
}
