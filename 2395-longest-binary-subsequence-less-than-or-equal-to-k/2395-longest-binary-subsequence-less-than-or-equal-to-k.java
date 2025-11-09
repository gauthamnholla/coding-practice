public class Solution {
    public int longestSubsequence(String s, int k) {
        int n = s.length();
        int zeros = 0, ones = 0;
        long value = 0; // Current binary value
        long pow = 1;   // Power of 2 (bit weight)

        // Count all zeros (they can always be part of the subsequence)
        for (char c : s.toCharArray()) {
            if (c == '0') zeros++;
        }

        // Traverse string from right to left (least significant bit first)
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '1') {
                // Add '1' only if total value stays ≤ k
                if (value + pow > k) continue;
                value += pow;
                ones++;
            }
            pow <<= 1; // Move to next higher bit

            // Stop if next bit value already exceeds k
            if (pow > k) break;
        }

        return zeros + ones; // Total subsequence length
    }
}
