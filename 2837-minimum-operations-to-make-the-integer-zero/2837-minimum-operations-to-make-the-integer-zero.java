class Solution {
    public int makeTheIntegerZero(int num1, int num2) {
        // Try for k = 1 to 60 (since bit operations fit within this range)
        for (int k = 1; k <= 60; k++) {
            // Compute x = num1 - k * num2
            long x = 1L * num1 - 1L * num2 * k;

            // If x becomes smaller than k, it's impossible
            if (x < k) {
                return -1;
            }

            // If k is greater than or equal to the number of 1-bits in x
            // then it is possible to represent x using k terms
            if (k >= Long.bitCount(x)) {
                return k;
            }
        }

        // If no valid k found, return -1
        return -1;
    }
}
