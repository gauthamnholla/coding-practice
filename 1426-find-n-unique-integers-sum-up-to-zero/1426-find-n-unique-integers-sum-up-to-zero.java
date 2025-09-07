class Solution {
    public int[] sumZero(int n) {
        int[] a = new int[n]; // Result array of size n
        int idx = 0;          // Current index for inserting elements

        // If n is odd, include 0 (so the sum can still be 0)
        if (n % 2 != 0) {
            a[idx++] = 0;
        }

        // Fill the array with pairs (i, -i)
        // This ensures sum stays balanced at 0
        for (int i = 1; i <= n / 2; i++) {
            a[idx++] = i;   // Positive number
            a[idx++] = -i;  // Its negative counterpart
        }

        return a; // Return the array where all numbers sum to 0
    }
}
