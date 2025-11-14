class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> seen = new HashMap<>(); // Store number → last index

        // Loop through each number
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];

            // Check if same number appeared within distance k
            if (seen.containsKey(val) && i - seen.get(val) <= k) {
                return true;
            }

            // Update the latest index of the number
            seen.put(val, i);
        }

        return false; // No nearby duplicates found
    }
}
