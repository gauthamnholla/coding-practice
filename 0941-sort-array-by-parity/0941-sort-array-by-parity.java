class Solution {
    public int[] sortArrayByParity(int[] nums) {
        int left = 0; // Pointer to place the next even number

        for (int i = 0; i < nums.length; i++) {
            // Check if current number is even
            if (nums[i] % 2 == 0) {
                // Swap even number to the 'left' position
                int temp = nums[left];
                nums[left] = nums[i];
                nums[i] = temp;

                left++; // Move pointer to next position
            }
        }

        return nums; // Array now has evens first, odds after
    }
}
