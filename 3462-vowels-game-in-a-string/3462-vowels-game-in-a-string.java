class Solution {
    public boolean doesAliceWin(String s) {
        // Iterate over each character in the string
        for (int i = 0; i < s.length(); i++) {
            // (s.charAt(i) - 97) -> gives position of letter ('a' = 0, 'b' = 1, etc.)
            // 0x104111 is a bitmask where bits for vowels (a, e, i, o, u) are set.
            // Shift the mask and check if current char is a vowel.
            if ((0x104111 >> (s.charAt(i) - 97) & 1) != 0)
                return true; // If vowel found, Alice wins
        }
        // No vowels found in the string
        return false;
    }
}
