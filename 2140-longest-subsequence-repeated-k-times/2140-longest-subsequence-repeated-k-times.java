class Solution {
    // Check if 'pattern' can appear k times as a subsequence in 'source'
    private boolean isRepeatedKTimes(char[] source, String pattern, int k) {
        char[] pat = pattern.toCharArray();
        int sourceLen = source.length;
        int patLen = pat.length;
        int idx = 0;

        while (k-- > 0) {
            int match = 0;
            while (idx < sourceLen && match < patLen) {
                if (source[idx] == pat[match]) match++; // Match pattern char
                idx++;
            }
            if (match != patLen) return false; // Pattern not fully matched
        }
        return true; // Pattern repeated k times successfully
    }

    public String longestSubsequenceRepeatedK(String s, int k) {
        char[] chars = s.toCharArray();
        int len = chars.length;

        int[] freq = new int[26]; // Store char frequency
        for (int i = 0; i < len; i++) {
            freq[chars[i] - 'a']++;
        }

        // Candidate lists by pattern length (max length = 7)
        ArrayList<String>[] candidates = new ArrayList[8];
        candidates[1] = new ArrayList<>();
        String result = "";

        // Add single characters that appear at least k times
        for (int i = 0; i < 26; i++) {
            if (freq[i] >= k) {
                String ch = "" + (char) ('a' + i);
                result = ch;
                candidates[1].add(ch);
            }
        }

        // Build longer patterns using smaller valid ones
        for (int length = 2; length < 8; length++) {
            candidates[length] = new ArrayList<>();
            for (String prefix : candidates[length - 1]) {
                for (String suffix : candidates[1]) {
                    String combo = prefix + suffix; // Try extending
                    if (isRepeatedKTimes(chars, combo, k)) {
                        result = combo; // Update result if valid
                        candidates[length].add(combo);
                    }
                }
            }
        }

        return result; // Longest subsequence repeated k times
    }
}
