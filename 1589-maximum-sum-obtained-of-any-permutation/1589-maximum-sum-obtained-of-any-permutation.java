class Solution {
    private static final int MOD = 1000000007;

    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        int n = nums.length;
        int[] frequency = new int[n];
        
        // Step 1: Use difference array to count frequencies
        for (int[] request : requests) {
            int start = request[0];
            int end = request[1];
            frequency[start]++;
            if (end + 1 < n) {
                frequency[end + 1]--;
            }
        }
        
        // Calculate prefix sum to get actual frequencies
        for (int i = 1; i < n; i++) {
            frequency[i] += frequency[i - 1];
        }

        // Step 2: Sort both arrays
        Arrays.sort(frequency);
        Arrays.sort(nums);

        // Step 3: Multiply and sum
        long totalSum = 0;
        for (int i = 0; i < n; i++) {
            totalSum += (long) frequency[i] * nums[i];
        }

        return (int) (totalSum % MOD);
    }
}