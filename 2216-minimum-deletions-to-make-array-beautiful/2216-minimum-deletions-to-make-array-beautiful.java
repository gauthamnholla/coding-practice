class Solution {
    public int minDeletion(int[] nums) {
        int deletions = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            // After deletions, effective index in the new array is (i - deletions)
            // A position is "even" if (i - deletions) % 2 == 0
            if ((i - deletions) % 2 == 0 && nums[i] == nums[i + 1]) {
                deletions++;
            }
        }

        // After all deletions, if length is odd → need one more deletion
        if ((nums.length - deletions) % 2 != 0) {
            deletions++;
        }

        return deletions;
    }
}

