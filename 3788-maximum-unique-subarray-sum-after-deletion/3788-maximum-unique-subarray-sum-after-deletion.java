class Solution {
    public int maxSum(int[] nums) {
        // Assume all elements are negative initially
        boolean allNegative = true;
        // Variable to track the largest number in the array
        int maxValue = Integer.MIN_VALUE;

        // Scan through nums to check if there's any non-negative number, and find the maximum value
        for (int n : nums) {
            if (n >= 0) {
                allNegative = false; // Found at least one non-negative number
            }
            if (n > maxValue) {
                maxValue = n; // Update max value found so far
            }
        }

        // If all numbers are negative, return the largest (least negative) number
        if (allNegative)
            return maxValue;

        // Boolean array to keep track of which unique non-negative numbers exist (from 0 to 100)
        boolean[] seen = new boolean[101];
        for (int n : nums) {
            if (n >= 0 && n < 101) {
                seen[n] = true; // Mark this number as seen
            }
        }

        // Calculate the sum of all positive unique numbers (from 1 to 100)
        int sum = 0;
        for (int i = 1; i < 101; i++) {
            if (seen[i]) {
                sum += i;
            }
        }

        // Return the sum of unique positive numbers (or 0 if none were present except possible 0)
        return sum;
    }
}
