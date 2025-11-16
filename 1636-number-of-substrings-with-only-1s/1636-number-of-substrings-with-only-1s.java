class Solution {
    public int numSub(String s) {
        final int mod = 1000000007; // To handle large results
        long cnt = 0, ans = 0;

        // Loop through each character in the string
        for (char c : s.toCharArray()) {
            // When we hit '0', add count of consecutive '1's so far
            ans += (1 - (c - '0')) * cnt * (cnt + 1) / 2;

            // Update count: if '1', increment streak; if '0', reset to 0
            cnt = (c - '0') * (cnt + 1);
        }

        // Add contribution from the last group of '1's
        ans += cnt * (cnt + 1) / 2;

        return (int) (ans % mod); // Return result under modulo
    }
}
