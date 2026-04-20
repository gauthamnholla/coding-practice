import java.util.*;

class Solution {
    int n, k;
    int[] nums;
    int[] digitCounts;
    List<Integer>[][] dp;
    private static final List<Integer> NOT_FOUND = new ArrayList<>();

    public int[] concatenatedDivisibility(int[] nums, int k) {
        this.k = k;
        this.n = nums.length;
        this.nums = nums;
        Arrays.sort(nums);
        digitCounts = new int[n];
        for (int i = 0; i <  nums.length; i++) {
            int num = nums[i];
            int digitCount = 0;
            while (num > 0) {
                digitCount++;
                num /= 10;
            }
            digitCounts[i] = digitCount;
        }
        this.dp = new List[1 << n][k];
        List<Integer> result = solve(0, 0);
        int[] ans = new int[result.size()];
        for (int i = 0; i < result.size(); i++) ans[i] = result.get(i);
        return ans;
    }

    // returns the smallest permutation of the remaining elements
    // when the current remainder for the already processed items is curRem
    // processed items are represented by 1s in mask as bits.
    List<Integer> solve(int mask, int curRem) {
        if (mask == (1 << n) - 1) {
            if (curRem == 0) {
                dp[mask][curRem] = new ArrayList<>();
                return dp[mask][curRem];
            }
            else {
                dp[mask][curRem] = NOT_FOUND;
                return NOT_FOUND;
            }
        }

        if (dp[mask][curRem] != null) return dp[mask][curRem];

        List<Integer> ans = NOT_FOUND;

        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) == 0) {
                // visit i
                int newCurRem = ((int)(curRem * Math.pow(10, digitCounts[i])) + nums[i]) % k;
                if (newCurRem < 0) newCurRem += k;

                List<Integer> solution = solve(mask | (1 << i), newCurRem);
                if (solution != NOT_FOUND) {
                    List<Integer> copy = new ArrayList<>(solution);
                    copy.addFirst(nums[i]);
                    ans = copy;
                    break;
                }
            }
        }

        dp[mask][curRem] = ans;
        return dp[mask][curRem];
    }
}