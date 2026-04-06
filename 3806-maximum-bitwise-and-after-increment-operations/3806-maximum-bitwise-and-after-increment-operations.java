class Solution {
    public int maximumAND(int[] nums, int k, int m) {
        int mx = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
        }

        int[] ops = new int[nums.length]; // Number of operations for each number
        int ans = 0;
        int maxWidth = 32 - Integer.numberOfLeadingZeros(mx + k / m);
        for (int bit = maxWidth - 1; bit >= 0; bit--) {
            int target = ans | (1 << bit); // Note: target includes the bits already set in ans
            for (int i = 0; i < nums.length; i++) {
                int x = nums[i];
                int j = 32 - Integer.numberOfLeadingZeros(target & ~x);
                // j-1 is the highest bit where target is 1 and x is 0
                int mask = (1 << j) - 1;
                ops[i] = (target & mask) - (x & mask);
            }

            // Greedy: pick the smallest m operation counts
            Arrays.sort(ops);
            long sum = 0;
            for (int i = 0; i < m; i++) {
                sum += ops[i];
            }
            if (sum <= k) {
                ans = target; // This bit of the answer can be set to 1
            }
        }
        return ans;
    }
}