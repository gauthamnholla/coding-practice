class Solution {

    public int numberOfSubstrings(String s) {
        int n = s.length();
        int[] pre = new int[n + 1];
        pre[0] = -1;

        // Step 1: Precompute last position of '0' before each index
        for (int i = 0; i < n; i++) {
            if (i == 0 || s.charAt(i - 1) == '0') {
                pre[i + 1] = i; // Store position of previous '0'
            } else {
                pre[i + 1] = pre[i]; // Carry forward last '0' position
            }
        }

        int res = 0;

        // Step 2: Iterate through each position in the string
        for (int i = 1; i <= n; i++) {
            int cnt0 = s.charAt(i - 1) == '0' ? 1 : 0;
            int j = i;

            // Step 3: Move backward while counting valid substrings
            while (j > 0 && cnt0 * cnt0 <= n) {
                int cnt1 = (i - pre[j]) - cnt0; // Number of 1's in substring

                // Check if substring satisfies the condition: (zeros² ≤ ones)
                if (cnt0 * cnt0 <= cnt1) {
                    res += Math.min(j - pre[j], cnt1 - cnt0 * cnt0 + 1);
                }

                j = pre[j]; // Move to previous '0' position
                cnt0++;
            }
        }

        return res; // Total valid substrings
    }
}
