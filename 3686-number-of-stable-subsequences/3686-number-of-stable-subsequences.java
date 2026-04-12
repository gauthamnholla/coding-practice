class Solution {
        public int countStableSubsequences(int[] A) {
        long mod = 1_000_000_007L;
        long[] odd = {1, 0, 0};
        long[] even = {0, 0, 0};
        for (int a : A) {
            if ((a & 1) == 1) {
                odd[2] = (odd[2] + odd[1]) % mod;
                odd[1] = (odd[1] + odd[0] + even[0] + even[1] + even[2]) % mod;
            } else {
                even[2] = (even[2] + even[1]) % mod;
                even[1] = (even[1] + even[0] + odd[0] + odd[1] + odd[2]) % mod;
            }
        }
        long res = (odd[1] + odd[2] + even[1] + even[2]) % mod;
        return (int)res;
    }
}