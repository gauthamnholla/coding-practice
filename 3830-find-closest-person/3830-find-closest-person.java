class Solution {
    public int findClosest(int x, int y, int z) {
        // Calculate absolute difference between x and z
        int a = Math.abs(x - z);

        // Calculate absolute difference between y and z
        int b = Math.abs(y - z);

        // Return code based on which is closer:
        // - If a > b → return 2 (y is closer)
        // - If a < b → return 1 (x is closer)
        // - If a == b → return 0 (both are equally close)
        return ((a > b) ? 2 : 0) | ((a < b) ? 1 : 0);
    }
}
