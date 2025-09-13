class Solution {
    public int maxFreqSum(String s) {
        // Array to store frequency of each character (a-z)
        int[] freq = new int[26];

        // Track maximum frequency of vowels and consonants
        int maxVowel = 0, maxConso = 0;

        // Step 1: Count frequency of each character in the string
        for (char c : s.toCharArray()) {
            int i = c - 'a'; // Map character to index (0-25)
            freq[i]++;

            // Step 2: Update max frequency for vowels and consonants separately
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
                maxVowel = Math.max(maxVowel, freq[i]);
            else
                maxConso = Math.max(maxConso, freq[i]);
        }

        // Step 3: Return sum of highest vowel frequency + highest consonant frequency
        return maxVowel + maxConso;
    }
}
