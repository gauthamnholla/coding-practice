class Solution {
    public int numPermsDISequence(String s) {
        int mod = 1000000007;
        int n = s.length();
        int[][] dp = new int [n+1][n+1];
        Arrays.fill(dp[0],1);
        for(int i=1;i<=n;i++){
            for(int j=0;j<=i;j++){
                if(s.charAt(i-1)=='D'){
                    for(int k=j;k<i;k++){
                        dp[i][j] += dp[i-1][k];
                        dp[i][j] %= mod;
                    }
                }
                else {
                    for(int k=0;k<j;k++){
                        dp[i][j] += dp[i-1][k];
                        dp[i][j] %= mod;
                    }
                }
            }
        }
        int ans = 0;
        for(int x:dp[n]){
            ans += x;
            ans %= mod;
        }
        return ans;
    }
}