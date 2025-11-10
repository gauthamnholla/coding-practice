class Solution {
    // Store previously solved subproblems (memoization)
    Map<String, Boolean> mp = new HashMap<>();

    public boolean isScramble(String s1, String s2) {
        int n = s1.length();

        // If lengths are different, not possible
        if (s2.length() != n) return false;

        // If both strings are same
        if (s1.equals(s2)) return true;

        // If single character and not equal → false
        if (n == 1) return false;

        String key = s1 + " " + s2;
        // Return already computed result
        if (mp.containsKey(key)) return mp.get(key);

        // Try splitting the string at every position
        for (int i = 1; i < n; i++) {
            // Case 1: Without swapping parts
            boolean withoutswap =
                isScramble(s1.substring(0, i), s2.substring(0, i)) &&
                isScramble(s1.substring(i), s2.substring(i));

            if (withoutswap) {
                mp.put(key, true);
                return true;
            }

            // Case 2: With swapping parts
            boolean withswap =
                isScramble(s1.substring(0, i), s2.substring(n - i)) &&
                isScramble(s1.substring(i), s2.substring(0, n - i));

            if (withswap) {
                mp.put(key, true);
                return true;
            }
        }

        // No valid scramble found
        mp.put(key, false);
        return false;
    }
}
