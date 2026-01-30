class Solution {
    public boolean isGoodArray(int[] nums) {
        int gcdSoFar = 0;   // start with 0 (gcd(0, x) = x)

        for (int num : nums) {
            gcdSoFar = gcd(gcdSoFar, num);
            if (gcdSoFar == 1) {
                return true;   //for early exit as we find the soliution.
            }
        }
        return false;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}