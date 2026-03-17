class Solution {
    public int minOperations(List<Integer> nums, int k) {
        boolean[] collected = new boolean[k + 1]; // collected[i] = true if we have element i
        int count = 0; // how many distinct elements 1..k we've found
        int operations = 0;

        // Scan from the end (since we remove from the end)
        for (int i = nums.size() - 1; i >= 0; i--) {
            operations++;
            int val = nums.get(i);

            // Only care about values in range 1..k
            if (val <= k && !collected[val]) {
                collected[val] = true;
                count++;
            }

            // Stop as soon as we have all elements 1..k
            if (count == k) {
                return operations;
            }
        }

        return operations;
    }
}