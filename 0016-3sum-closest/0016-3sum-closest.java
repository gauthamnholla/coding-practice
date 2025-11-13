class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums); // Sort the array
        int n = nums.length;
        int result = nums[0] + nums[1] + nums[2]; // Initial closest sum

        // Loop through each element
        for (int i = 0; i < n - 2; i++) {
            int left = i + 1, right = n - 1;

            // Two-pointer approach
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                // Update closest sum if current is better
                if (Math.abs(target - sum) < Math.abs(target - result)) {
                    result = sum;
                }

                // Move pointers based on comparison
                if (sum == target) return target; // Perfect match
                else if (sum < target) left++;
                else right--;
            }
        }

        return result; // Return closest sum found
    }
}
