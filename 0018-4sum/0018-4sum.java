class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> li = new ArrayList<>();
        if (nums == null || nums.length < 4) return li; // Not enough numbers

        Arrays.sort(nums); // Sort array for two-pointer technique

        // Fix the first two numbers
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // Skip duplicates

            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue; // Skip duplicates

                int left = j + 1, right = nums.length - 1;

                // Two-pointer approach for the last two numbers
                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        li.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // Skip duplicates for left and right
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;

                        left++;
                        right--;
                    } else if (sum < target) {
                        left++; // Need a larger sum
                    } else {
                        right--; // Need a smaller sum
                    }
                }
            }
        }

        return li;
    }
}
