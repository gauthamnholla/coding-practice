class Solution {
        public int maximumANDSum(int[] A, int ns) {
        int mask = (int)Math.pow(3, ns) - 1;
        int[] memo = new int[mask + 1];
        return dp(A.length - 1, mask, ns, memo, A);
    }
    
    private int dp(int i, int mask, int ns, int[] memo, int[] A) {
        if (memo[mask] > 0) return memo[mask];
        if (i < 0) return 0;
        for (int slot = 1, bit = 1; slot <= ns; ++slot, bit*= 3)
            if (mask / bit % 3 > 0)
                memo[mask] = Math.max(memo[mask], (A[i] & slot) + dp(i - 1, mask - bit, ns, memo, A));
        return memo[mask];
    }
}