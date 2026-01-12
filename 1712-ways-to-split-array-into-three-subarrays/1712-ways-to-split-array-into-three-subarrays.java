class Solution {
    public int waysToSplit(int[] nums) {
        int MOD = 1_000_000_007;
        int n = nums.length;

        // Step 1: Build prefix sum array
        int[] prefix = new int[n];
        prefix[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }

        int totalWays = 0;

        // Step 2: Iterate through the first cut position (i)
        // i is the end index of the left subarray
        for (int i = 0; i < n - 2; i++) {
            int leftSum = prefix[i];

            // Step 3a: Binary search to find first valid j (start of right subarray)
            int low = i + 1;
            int high = n - 2;
            int first = -1;

            while (low <= high) {
                int mid = low + (high - low) / 2;
                int midSum = prefix[mid] - prefix[i];

                if (midSum >= leftSum) {
                    // Valid mid found; try to find an earlier one
                    first = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            // If no valid j found, skip this i
            if (first == -1) continue;

            // Step 3b: Binary search to find last valid j
            low = first;
            high = n - 2;
            int last = -1;

            while (low <= high) {
                int mid = low + (high - low) / 2;
                int midSum = prefix[mid] - prefix[i];
                int rightSum = prefix[n - 1] - prefix[mid];

                if (midSum <= rightSum) {
                    // Valid mid found; try to find a later one
                    last = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            // Step 4: Add number of valid splits for this i
            if (last != -1) {
                totalWays = (totalWays + (last - first + 1)) % MOD;
            }
        }

        return totalWays;
    }
}