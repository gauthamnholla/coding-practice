class Solution {
    static int[] primes = {2,3,5,7,11,13,17,19,23,29};
    static int mod = 1000000007;

    public int numberOfGoodSubsets(int[] nums){
        int[] count = new int[31];
        for (int num : nums) count[num]++;

        int[] masked = new int[31];
        for(int i=2 ; i<31 ; i++){
            int mask=0; //initially
            int temp=i;
            for(int j=0 ; j<10 ; j++){
                int prime = primes[j];
                int cnt=0;
                while(temp%prime==0){
                    temp/=prime;
                    cnt++;
                }
                if(cnt>1){
                    mask=-1;
                    break;
                }
                if(cnt==1){
                    // included exactly one so now needed masking
                    // masking is done on indices of prime numbers array
                    mask = mask + (1<<j); // mask + 2^j 
                }
            }
            masked[i] = mask;
        }
        long[] dp = new long[1024]; //2^10 here why 10 as length of primes
        dp[0] = 1;
        for(int i=2 ; i<=30 ; i++){
            if(count[i]==0 || masked[i]==-1) continue;

            int curMask = masked[i];
            // cloning as directly writing in dp can lead to loss of data
            long[] next = dp.clone();

            for (int m = 0; m < 1024; m++) {
                if ((m & curMask) == 0) {
                    next[m | curMask] = (next[m | curMask] + dp[m] * count[i]) % mod;
                }
            }

            dp = next;

        }
        long ans = 0;
        for (int i = 1; i < 1024 ; i++){
            ans = (ans + dp[i]) % mod;
        }

        long pow = mod2Pow(count[1])%mod;

        return (int)(ans*pow%mod);
    }
    private long mod2Pow(int exp) {
        long res = 1;
        while (exp-- > 0) res = (res*2)%mod;
        return res;
    }
}