class Solution {
    // Precomputed C(n, k) % 5 for n, k < 5
    private final int[][] pascal_mod_5 = {
        {1, 0, 0, 0, 0},
        {1, 1, 0, 0, 0},
        {1, 2, 1, 0, 0},
        {1, 3, 3, 1, 0},
        {1, 4, 1, 4, 1}
    };

    /**
     * Calculates C(n_val, k_val) % 5 using Lucas's Theorem.
     */
    private int get_lucas_mod5(int n_val, int k_val) {
        if (k_val < 0 || k_val > n_val) {
            return 0;
        }
        
        long res = 1; // Use long for intermediate product
        
        // Process digits in base 5
        while (n_val > 0 || k_val > 0) {
            int n_digit = n_val % 5;
            int k_digit = k_val % 5;
            
            // C(n, k) % p = PROD( C(n_i, k_i) % p )
            res = (res * this.pascal_mod_5[n_digit][k_digit]) % 5;
            
            if (res == 0) {
                // If any C(n_i, k_i) is 0, the whole product is 0.
                break;
            }
                
            n_val /= 5;
            k_val /= 5;
        }
        
        return (int)res;
    }

    /**
     * Checks if the final two digits after all operations are the same.
     */
    public boolean hasSameDigits(String s) {
        int n = s.length();
        if (n == 2) {
            return s.charAt(0) == s.charAt(1);
        }
        
        // Convert string to an array of integers
        int[] nums = new int[n];
        for (int i = 0; i < n; ++i) {
            nums[i] = s.charAt(i) - '0';
        }
        
        // k = number of operations = n - 2
        int k = n - 2;
        // m = n - 1 (used for mod 2 check)
        int m = n - 1;

        // 1. Check Modulo 2
        // We check if (SUM(C(m, i) * d[i] for i=0..m)) % 2 == 0
        int sum_mod2 = 0;
        for (int i = 0; i < n; ++i) { // i from 0 to m (which is n-1)
            // C(m, i) % 2 == 1 iff (m & i) == i (Lucas's Theorem for p=2)
            if ((m & i) == i) {
                sum_mod2 = (sum_mod2 + nums[i]) % 2;
            }
        }
        
        if (sum_mod2 != 0) {
            // D0 % 2 != D1 % 2, so they can't be equal
            return false;
        }

        // 2. Check Modulo 5
        // We check if D0 % 5 == D1 % 5
        // D0 = (SUM(C(k, i) * d[i] for i=0..k)) % 5
        // D1 = (SUM(C(k, i) * d[i+1] for i=0..k)) % 5
        
        int d0_mod5 = 0;
        int d1_mod5 = 0;
        
        for (int i = 0; i <= k; ++i) { // i from 0 to k
            // Get C(k, i) % 5 using Lucas's Theorem
            int coeff_mod5 = get_lucas_mod5(k, i);
            
            if (coeff_mod5 != 0) {
                // Use long for intermediate multiplication to avoid overflow before modulo
                d0_mod5 = (int)((d0_mod5 + (long)coeff_mod5 * nums[i]) % 5);
                d1_mod5 = (int)((d1_mod5 + (long)coeff_mod5 * nums[i+1]) % 5);
            }
        }

        if (d0_mod5 != d1_mod5) {
            // D0 % 5 != D1 % 5, so they can't be equal
            return false;
        }
        
        // 3. Final Result
        // If both mod 2 and mod 5 checks pass, then D0 % 10 == D1 % 10
        return true;
    }
}