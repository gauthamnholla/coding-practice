class Solution {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 1;

        // Step 1: Find the first decreasing element from the end
        while (i > 0 && nums[i - 1] >= nums[i]) {
            i--;
        }

        // If array is in descending order, reverse it to smallest permutation
        if (i == 0) {
            reverse(nums, 0, nums.length - 1);
            return;
        }

        // Step 2: Find the element just larger than nums[i-1] to swap
        int j = nums.length - 1;
        while (j >= i && nums[j] <= nums[i - 1]) {
            j--;
        }

        // Step 3: Swap the two elements
        swap(nums, i - 1, j);

        // Step 4: Reverse the remaining right half to get next smallest order
        reverse(nums, i, nums.length - 1);
    }

    // Swap helper
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // Reverse helper
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
