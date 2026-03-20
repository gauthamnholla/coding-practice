class Solution {
        public int maxSubarrays(int[] A) {
        int v = -1, res = 0;
        for (int a: A) {
            v &= a;
            if (v == 0) {
                v = -1;
                res += 1;
            }
        }
        return Math.max(1, res);
    }
}