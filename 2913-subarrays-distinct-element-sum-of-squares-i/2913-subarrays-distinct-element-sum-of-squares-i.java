class Solution {
    public int sumCounts(List<Integer> nums) {
        int n = nums.size();
        int result = 0;
        
        // dp[i] = distinct count of subarray nums[i..j]
        int[] dp = new int[n];
        // last[v] = last index where value v was seen, -1 if never
        int[] last = new int[101];
        Arrays.fill(last, -1);

        for (int j = 0; j < n; j++) {
            int v = nums.get(j);
            int lastSeen = last[v]; // last index where v appeared
            last[v] = j;

            // For subarrays starting at i <= lastSeen, v was already
            // present, so distinct count doesn't change.
            // For subarrays starting at i > lastSeen, v is new,
            // so distinct count increases by 1.
            for (int i = j; i > lastSeen; i--) {
                dp[i]++;
            }

            // Sum up squares
            for (int i = 0; i <= j; i++) {
                result += dp[i] * dp[i];
            }
        }

        return result;
    }
}