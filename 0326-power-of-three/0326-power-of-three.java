public class Solution {
    public boolean isPowerOfThree(int n) {
        // 3^19 = 1162261467 is the highest power of three in int range
        return n > 0 && 1162261467 % n == 0;
    }
}
