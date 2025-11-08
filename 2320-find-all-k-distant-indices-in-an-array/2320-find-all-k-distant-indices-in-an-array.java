class Solution {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        List<Integer> index = new ArrayList<>(); // Store result indices
        int n = nums.length;

        // Check each index i
        for (int i = 0; i < n; i++) {
            // Compare with every index j
            for (int j = 0; j < n; j++) {
                // If nums[j] is key and distance <= k
                if (nums[j] == key && Math.abs(i - j) <= k) {
                    index.add(i); // Add index i to result
                    break;        // Move to next i
                }
            }
        }

        return index; // Return all valid indices
    }
}
