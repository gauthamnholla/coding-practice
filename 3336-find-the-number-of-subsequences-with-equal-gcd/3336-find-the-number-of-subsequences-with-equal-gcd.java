class Solution {
    private final int MOD = 1_000_000_007;

    public int subsequencePairCount(int[] nums) {
        int n = nums.length;
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        Long[][][] dp = new Long[n][max + 1][max + 1];
        return (int) solve(nums, 0, 0, 0, dp);
    }

    private long solve(int[] nums, int i, int gcd1, int gcd2, Long[][][] dp) {
        if (i == nums.length) {
            return ((gcd1 > 0 && gcd2 > 0) && (gcd1 == gcd2)) ? 1 : 0;
        }

        if (dp[i][gcd1][gcd2] != null) {
            return dp[i][gcd1][gcd2];
        }


        long skipCurrentNumber = solve(nums, i + 1, gcd1, gcd2, dp);
        long takeCurrentNumberInSubsequence1 = solve(nums, i + 1, gcd(gcd1, nums[i]), gcd2, dp);
        long takeCurrentNumberInSubsequence2 = solve(nums, i + 1, gcd1, gcd(gcd2, nums[i]), dp);

        return dp[i][gcd1][gcd2] = ((skipCurrentNumber + takeCurrentNumberInSubsequence1) % MOD + takeCurrentNumberInSubsequence2) % MOD;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}