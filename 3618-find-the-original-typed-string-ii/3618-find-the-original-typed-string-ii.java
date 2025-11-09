public class Solution {
    private static final int MOD = (int)1e9 + 7;

    public int possibleStringCount(String word, int k) {
        if (word.isEmpty()) return 0;

        // Step 1: Group consecutive same characters
        List<Integer> groups = new ArrayList<>();
        int count = 1;
        for (int i = 1; i < word.length(); i++) {
            if (word.charAt(i) == word.charAt(i - 1)) count++;
            else {
                groups.add(count);
                count = 1;
            }
        }
        groups.add(count); // Add last group

        // Step 2: Multiply all group sizes (modulo MOD)
        long total = 1;
        for (int num : groups) total = (total * num) % MOD;

        // If k is within group count, return total
        if (k <= groups.size()) return (int)total;

        // Step 3: Dynamic Programming to count invalid cases
        int[] dp = new int[k];
        dp[0] = 1;
        for (int num : groups) {
            int[] newDp = new int[k];
            long sum = 0;
            for (int s = 0; s < k; s++) {
                if (s > 0) sum = (sum + dp[s - 1]) % MOD;           // Add previous
                if (s > num) sum = (sum - dp[s - num - 1] + MOD) % MOD; // Remove overflow
                newDp[s] = (int)sum;
            }
            dp = newDp;
        }

        // Step 4: Calculate invalid sequences
        long invalid = 0;
        for (int s = groups.size(); s < k; s++) invalid = (invalid + dp[s]) % MOD;

        // Step 5: Subtract invalid from total
        return (int)((total - invalid + MOD) % MOD);
    }
}
