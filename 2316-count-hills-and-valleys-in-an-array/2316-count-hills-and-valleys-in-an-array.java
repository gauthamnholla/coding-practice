import java.util.ArrayList;
import java.util.List;

class Solution {
    /**
     * Counts the number of hills and valleys in an array.
     *
     * @param nums The input integer array.
     * @return The total number of hills and valleys.
     */
    public int countHillValley(int[] nums) {
        // Step 1: Remove consecutive duplicates to simplify the problem.
        // Plateaus like [..., 4, 1, 1, 6, ...] become [..., 4, 1, 6, ...].
        // This makes finding "closest non-equal neighbors" trivial.
        List<Integer> uniqueNums = new ArrayList<>();
        if (nums.length > 0) {
            uniqueNums.add(nums[0]);
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                uniqueNums.add(nums[i]);
            }
        }

        // Step 2: A hill or valley requires a left and right neighbor.
        // So the compressed array must have at least 3 elements.
        if (uniqueNums.size() < 3) {
            return 0;
        }

        int count = 0;
        // Step 3: Iterate through the middle elements of the compressed array.
        // The first and last elements cannot be hills or valleys.
        for (int i = 1; i < uniqueNums.size() - 1; i++) {
            int left = uniqueNums.get(i - 1);
            int mid = uniqueNums.get(i);
            int right = uniqueNums.get(i + 1);

            // Check if 'mid' is a hill (peak)
            if (mid > left && mid > right) {
                count++;
            }
            // Check if 'mid' is a valley (trough)
            else if (mid < left && mid < right) {
                count++;
            }
        }

        return count;
    }
}
