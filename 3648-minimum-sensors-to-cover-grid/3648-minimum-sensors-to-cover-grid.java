class Solution {
    public int minSensors(int n, int m, int k) {
        long side = 2L * k + 1;
        long x = (n + side - 1) / side;
        long y = (m + side - 1) / side;
        return (int)(x * y);
    }
}