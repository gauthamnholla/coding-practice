class Solution {
    public int[] maxSubsequence(int[] nums, int k) {
        // Step 1: Store each element with its original index
        int[][] valueWithIndex = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            valueWithIndex[i] = new int[]{nums[i], i};
        }

        // Step 2: Sort by value in descending order
        Arrays.sort(valueWithIndex, (a, b) -> b[0] - a[0]);

        // Step 3: Sort top k elements by their original index
        Arrays.sort(valueWithIndex, 0, k, (a, b) -> a[1] - b[1]);

        // Step 4: Extract values for the final subsequence
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = valueWithIndex[i][0];
        }

        return result; // Return the subsequence
    }
}
