class Solution {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int duplicate = 0;
        int current_sum = 0;
        int original_sum = (n * (n + 1)) / 2; // Sum of numbers 1..n

        Arrays.sort(nums); // Sort to detect duplicate

        // Find duplicate and compute actual sum
        for (int i = 0; i < nums.length; i++) {
            current_sum += nums[i];

            // Duplicate found when two adjacent values are same
            if (i > 0 && nums[i] == nums[i - 1]) {
                duplicate = nums[i];
            }
        }

        // missing = expected_sum - (actual_sum - duplicate)
        int missing = original_sum - (current_sum - duplicate);

        return new int[]{duplicate, missing};
    }
}
