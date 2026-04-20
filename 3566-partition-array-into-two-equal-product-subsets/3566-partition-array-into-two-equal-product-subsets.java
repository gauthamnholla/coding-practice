import java.math.BigInteger;
class Solution {
    public boolean checkEqualPartitions(int[] nums, long target) {
        int n = nums.length;
        BigInteger T = BigInteger.valueOf(target);
        for (int mask = 1; mask <= (1 << n) - 1; mask++) {
            BigInteger[] a = { BigInteger.ONE, BigInteger.ONE };
            for (int i = 0; i < n; i++) {
                int idx = ((mask >> i) & 1) == 1 ? 1 : 0;
                a[idx] = a[idx].multiply(BigInteger.valueOf(nums[i]));
            }
            if (a[0].equals(T) && a[0].equals(a[1]))
                return true;
        }
        return false;
    }
}