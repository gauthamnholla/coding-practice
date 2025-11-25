class Solution {
    private static final int MOD = 1_000_000_007;
    private long[][] nCr;
    
    public int numOfWays(int[] nums) {
        int n = nums.length;
        
        // Precompute Pascal's triangle
        nCr = new long[n+1][n+1];
        for (int i = 0; i <= n; i++) {
            nCr[i][0] = nCr[i][i] = 1;
            for (int j = 1; j < i; j++) {
                nCr[i][j] = (nCr[i-1][j-1] + nCr[i-1][j]) % MOD;
            }
        }
        
        List<Integer> list = new ArrayList<>();
        for (int x : nums) list.add(x);
        
        return (int)((dfs(list) - 1 + MOD) % MOD);
    }
    
    private long dfs(List<Integer> nums) {
        if (nums.size() <= 2) return 1;
        
        int root = nums.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) < root) {
                left.add(nums.get(i));
            } else {
                right.add(nums.get(i));
            }
        }
        
        long leftWays = dfs(left);
        long rightWays = dfs(right);
        int L = left.size(), R = right.size();
        
        return (((nCr[L+R][L] * leftWays) % MOD) * rightWays) % MOD;
    }
}