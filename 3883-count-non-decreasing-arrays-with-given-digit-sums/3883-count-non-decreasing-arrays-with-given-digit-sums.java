import java.util.*;

class Solution {
    int MOD = (int)1e9 + 7;
    int[][] memo;

    boolean check(int s, int sum){
        int t = 0;
        while(s > 0){
            int lt = s % 10;
            t += lt;
            s /= 10;
        }
        return t == sum;
    }

    int solve(int idx, int s, int[] digitSum){
        if(idx == digitSum.length) return 1;
        if(s > 5000) return 0;

        if(memo[idx][s] != -1) return memo[idx][s];

        int res = 0;

        if(check(s, digitSum[idx])){
            res = (res + solve(idx + 1, s, digitSum)) % MOD;
        }

        res = (res + solve(idx, s + 1, digitSum)) % MOD;

        return memo[idx][s] = res;
    }

    public int countArrays(int[] digitSum) {
        int n = digitSum.length;

        memo = new int[n+1][5005];
        for(int[] row : memo) Arrays.fill(row, -1);

        return solve(0, 0, digitSum);
    }
}