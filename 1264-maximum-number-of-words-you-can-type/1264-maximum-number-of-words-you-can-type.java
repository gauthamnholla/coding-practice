class Solution {
    public int canBeTypedWords(String text, String broken) {
        // Step 1: Create a bitmask representing broken letters
        int mask = 0;
        for (int i = 0; i < broken.length(); i++)
            // Each broken letter sets a corresponding bit in 'mask'
            mask |= 1 << (broken.charAt(i) - 97);

        // Step 2: Initialize counters
        int count = 0;       // Counts words that can be typed
        int wordMask = 0;    // Tracks if current word has a broken letter

        // Step 3: Traverse the entire text (including an extra step at the end)
        for (int i = 0; i <= text.length(); i++) {
            
            if (i < text.length()) {
                // If still within text, update wordMask if this char is broken
                wordMask |= mask & (1 << (text.charAt(i) - 97));
            }

            // Step 4: End of word check (either space or end of string)
            if (i == text.length() || text.charAt(i) == ' ') {
                // If no broken letters in this word, count it
                if (wordMask == 0)
                    count++;
                // Reset wordMask for the next word
                wordMask = 0;
            }
        }

        // Step 5: Return the total count of words that can be typed
        return count;
    }
}
