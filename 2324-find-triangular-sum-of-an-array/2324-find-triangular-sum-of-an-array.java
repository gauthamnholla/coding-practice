class Solution {
    // Helper function: compute C(n, k) mod 10
    private int getMod10(int n, int k) {
        // Precomputed Pascal triangle mod 5 (rows for n=0..4, cols for k=0..4)
        int[][] fast5 = {
                { 1, 0, 0, 0, 0 },  // row for n=0
                { 1, 1, 0, 0, 0 },  // row for n=1
                { 1, 2, 1, 0, 0 },  // row for n=2
                { 1, 3, 3, 1, 0 },  // row for n=3
                { 1, 4, 1, 4, 1 }}; // row for n=4

        // Table of solutions for (mod 2, mod 5) → mod 10 using CRT
        int[][] sunzi = {
                { 0, 6, 2, 8, 4 },  // if mod2 = 0
                { 5, 1, 7, 3, 9 }}; // if mod2 = 1

        // Convert n and k to base-2 strings for Lucas theorem (mod 2)
        String n2 = Integer.toString(n, 2);
        String k2 = Integer.toString(k, 2);
        while (k2.length() < n2.length())
            k2 = "0" + k2;

        // Convert n and k to base-5 strings for Lucas theorem (mod 5)
        String n5 = Integer.toString(n, 5);
        String k5 = Integer.toString(k, 5);
        while (k5.length() < n5.length())
            k5 = "0" + k5;

        // Step 1: compute binomial coefficient C(n, k) mod 2 using Lucas theorem
        int mod2 = 1;
        for (int i = 0; i < n2.length(); i++) {
            // If k has a '1' in a bit where n has '0', then C(n, k) ≡ 0 mod 2
            if (k2.charAt(i) == '1' && n2.charAt(i) == '0') {
                mod2 = 0;
                break;
            }
        }

        // Step 2: compute binomial coefficient C(n, k) mod 5 using Lucas theorem
        int mod5 = 1;
        for (int i = 0; i < n5.length(); i++) {
            // Multiply contributions digit by digit in base 5 using Pascal’s triangle mod 5
            mod5 = (mod5 * fast5[n5.charAt(i) - '0'][k5.charAt(i) - '0']) % 5;
        }
        
        // Step 3: combine results mod 2 and mod 5 → mod 10 using CRT lookup table
        return sunzi[mod2][mod5];
    }

    public int triangularSum(int[] nums) {
        int n = nums.length - 1; // total number of steps (row index in Pascal’s triangle)
        int sum = 0;

        // Final sum = Σ C(n, k) * nums[k], all mod 10
        for (int k = 0; k <= n; k++) {
            int coeff = getMod10(n, k);   // binomial coefficient mod 10
            sum = (sum + coeff * nums[k]) % 10;
        }

        return sum;
    }
}
