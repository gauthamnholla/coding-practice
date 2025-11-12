class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int num1 = 0; // Count of 1s in array
        int g = 0;    // GCD of all numbers

        // Step 1: Count 1s and find total GCD
        for (int x : nums) {
            if (x == 1) num1++;
            g = gcd(g, x);
        }

        // If 1s already exist, only need to make others 1 → n - num1
        if (num1 > 0) return n - num1;

        // If overall GCD > 1, we can never make any 1
        if (g > 1) return -1;

        // Step 2: Find smallest subarray with GCD = 1
        int minLen = n;
        for (int i = 0; i < n; i++) {
            int currentGcd = 0;
            for (int j = i; j < n; j++) {
                currentGcd = gcd(currentGcd, nums[j]);
                if (currentGcd == 1) {
                    minLen = Math.min(minLen, j - i + 1);
                    break; // Stop once GCD=1 for this subarray
                }
            }
        }

        // Step 3: Operations = length to make 1 + (n - 1) more ops
        return minLen + n - 2;
    }

    // Helper: Compute GCD of two numbers
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
