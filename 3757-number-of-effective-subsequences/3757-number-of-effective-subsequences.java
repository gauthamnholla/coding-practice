class Solution {
    static final int MOD = 1_000_000_007;
    public int countEffective(int[] nums) {
        int n = nums.length;
        int full = 0;
        for (int v : nums) full |= v;
        if (full == 0) return 0;

        int[] bitsList = new int[31];
        int B = 0;
        for (int i=0;i<31;i++) if (((full>>i)&1)==1) bitsList[B++] = i;

        int[] freq = new int[1<<B];
        for (int v : nums) {
            int m = 0;
            for (int j=0;j<B;j++) if (((v>>bitsList[j])&1)==1) m |= 1<<j;
            freq[m]++;
        }

        int size = 1<<B;
        int[] dp = new int[size];
        for (int i=0;i<size;i++) dp[i] = freq[i];
        for (int i=0;i<B;i++){
            for (int mask=0; mask<size; mask++){
                if ((mask & (1<<i))!=0) dp[mask] += dp[mask ^ (1<<i)];
            }
        }

        long[] pow2 = new long[n+1];
        pow2[0]=1;
        for (int i=1;i<=n;i++) pow2[i] = (pow2[i-1]*2)%MOD;

        long ans = 0;
        int fullMask = size - 1;
        for (int s=1;s<size;s++){
            int comp = fullMask ^ s;
            int countZero = dp[comp];
            long term = pow2[countZero];
            if (Integer.bitCount(s)%2==1) ans = (ans + term) % MOD;
            else ans = (ans - term) % MOD;
        }
        ans = (ans % MOD + MOD) % MOD;
        return (int)ans;
    }
}