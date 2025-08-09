public class Solution {
    public boolean isPowerOfTwo(int n) {
        // Must be positive and only one bit set
        return n > 0 && (n & (n - 1)) == 0;
    }
}
