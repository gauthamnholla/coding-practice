class Solution {
    public char kthCharacter(int k) {
        StringBuilder sb = new StringBuilder("a"); // Start with "a"

        // Keep expanding the string until its length >= k
        while (sb.length() < k) {
            int size = sb.length();
            // For each existing char, add the next alphabet character
            for (int i = 0; i < size; i++) {
                sb.append((char) ('a' + ((sb.charAt(i) - 'a' + 1) % 26)));
            }
        }

        return sb.charAt(k - 1); // Return the k-th character (1-indexed)
    }
}
