
class Solution {
    public int minimumXORSum(int[] nums1, int[] nums2) {
        int n = nums1.length;

        int mask = 1<<n;
        int[] dp = new int[mask];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int i = 0;i < mask-1;i++){
            int j = Integer.bitCount(i);

            for(int k = 0;k < n;k++){
                if((i & (1<<k)) == 0){
                    dp[i | (1<<k)] = Math.min(dp[i | (1<<k)], dp[i] + (nums1[j] ^ nums2[k]));
                }
            }
        }

        return dp[mask-1];
    }
}