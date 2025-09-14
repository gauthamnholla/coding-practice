import java.util.*;

class Solution {
    // Helper: Check if a character is a vowel
    private boolean isVowel(char c) {
        return c=='a' || c=='e' || c=='i' || c=='o' || c=='u';
    }

    // Helper: Replace all vowels in the word with 'a' (for vowel-insensitive matching)
    private String maskVowels(String s) {
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (isVowel(arr[i])) arr[i] = 'a';
        }
        return new String(arr);
    }

    public String[] spellchecker(String[] wordlist, String[] queries) {
        // Exact match set
        Set<String> exact = new HashSet<>(Arrays.asList(wordlist));

        // Maps for case-insensitive and vowel-error matching
        Map<String,String> lowerMap = new HashMap<>(); // lowercase -> original word
        Map<String,String> vowelMap = new HashMap<>(); // masked vowels -> original word

        // Step 1: Populate maps from wordlist
        for (String w : wordlist) {
            String wl = w.toLowerCase(Locale.ROOT);

            // First occurrence of lowercase version
            lowerMap.putIfAbsent(wl, w);

            // Mask vowels and store first occurrence
            String masked = maskVowels(wl);
            vowelMap.putIfAbsent(masked, w);
        }

        // Step 2: Answer each query
        String[] ans = new String[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String q = queries[i];

            // Rule 1: Exact match
            if (exact.contains(q)) {
                ans[i] = q;
                continue;
            }

            // Rule 2: Case-insensitive match
            String ql = q.toLowerCase(Locale.ROOT);
            if (lowerMap.containsKey(ql)) {
                ans[i] = lowerMap.get(ql);
                continue;
            }

            // Rule 3: Vowel-error match (treat all vowels as 'a')
            String qMasked = maskVowels(ql);
            if (vowelMap.containsKey(qMasked)) {
                ans[i] = vowelMap.get(qMasked);
            } else {
                ans[i] = ""; // No match
            }
        }

        return ans;
    }
}
