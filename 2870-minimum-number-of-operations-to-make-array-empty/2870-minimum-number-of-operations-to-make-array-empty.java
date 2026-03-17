class Solution {
    public int minOperations(int[] nums) {
        // Step 1: Count frequency of each value
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        int totalOps = 0;

        // Step 2: For each unique value, compute min operations
        for (int count : freq.values()) {

            // Impossible: can't split 1 into groups of 2 or 3
            if (count == 1) return -1;

            // ceil(count / 3)
            totalOps += Math.ceil((double) count / 3);
        }

        return totalOps;
    }
}