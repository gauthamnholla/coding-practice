class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        // Binary search in rotated sorted array
        while (left <= right) {
            int mid = (left + right) / 2;

            // Found target
            if (nums[mid] == target) return mid;

            // Left half is sorted
            if (nums[mid] >= nums[left]) {
                // Check if target lies within the left half
                if (nums[left] <= target && target <= nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // Right half is sorted
            else {
                // Check if target lies within the right half
                if (nums[mid] <= target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1; // Target not found
    }
}
