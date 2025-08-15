public class Solution {
    public boolean isPowerOfFour(int n) {
        // 1. n > 0
        // 2. n is power of 2 (only one bit set): (n & (n-1)) == 0
        // 3. set bit is in even position: (n & 0xAAAAAAAA) == 0
        //    (0xAAAAAAAA is binary 101010... pattern, i.e., all "odd" bits)
        return n > 0 
            && (n & (n - 1)) == 0 
            && (n & 0xAAAAAAAA) == 0;
    }
}
