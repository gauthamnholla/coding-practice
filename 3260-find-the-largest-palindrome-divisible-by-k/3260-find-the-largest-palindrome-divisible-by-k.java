import java.util.Arrays;
import java.util.Vector;

class Solution {

    public int find(int ind, int premod, int n, int k, int[] power, int[][] dp, int[] res, int half) {

        // Base case
        if (ind == half) {
            return premod == 0 ? 1 : 0;
        }

        if (dp[ind][premod] != -1) {
            return dp[ind][premod];
        }

        for (int digit = 9; digit >= 0; digit--) {
            int newmod = premod;
            if ((n % 2 != 0) && (ind == half - 1)) {
                newmod += (digit * power[ind]) % k;
            } else {
                newmod += (digit * power[ind]) % k;
                newmod += (digit * power[n - 1 - ind]) % k;
            }

            newmod = newmod % k;

            int flag = find(ind + 1, newmod, n, k, power, dp, res, half);
            if (flag == 1) {
                res[ind] = digit;
                break;
            }
        }

        if (res[ind] == -1) {
            dp[ind][premod] = 0;
        } else {
            dp[ind][premod] = 1;
        }

        return dp[ind][premod];
    }

    public String largestPalindrome(int n, int k) {

        int half = (n + 1) / 2;

        int[] power = new int[n];
        power[0] = 1 % k;

        for (int i = 1; i < n; i++) {
            power[i] = (power[i - 1] * 10) % k;
        }

        int[] res = new int[n];
        Arrays.fill(res, -1);
        int[][] dp = new int[half][10];
        for (int[] row : dp) Arrays.fill(row, -1);

        int prev = 0;
        find(0, prev, n, k, power, dp, res, half);

        char[] ans = new char[n];
        for (int i = 0; i < half; i++) {
            ans[i] = (char) ('0' + res[i]);
            ans[n - 1 - i] = ans[i];
        }

        return new String(ans);
    }
}