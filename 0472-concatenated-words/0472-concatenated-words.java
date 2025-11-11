class Solution {
    Map<String, Boolean> mp = new HashMap<>(); // Memoization map

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Set<String> set = new HashSet<>();
        List<String> result = new ArrayList<>();

        // Step 1: Add all words to a set for quick lookup
        for (String word : words) {
            set.add(word);
        }

        // Step 2: Check each word if it's a concatenated word
        for (String word : words) {
            if (isConcatenated(word, set)) {
                result.add(word);
            }
        }

        return result; // Step 3: Return all concatenated words
    }

    private boolean isConcatenated(String word, Set<String> set) {
        // If already computed, return from memo
        if (mp.containsKey(word)) return mp.get(word);

        int m = word.length();
        // Try splitting the word into prefix and suffix
        for (int i = 0; i < m; i++) {
            String prefix = word.substring(0, i + 1);
            String suffix = word.substring(i + 1);

            // If prefix exists and suffix is concatenated or exists
            if ((set.contains(prefix) && isConcatenated(suffix, set)) ||
                (set.contains(prefix) && set.contains(suffix))) {
                mp.put(word, true);
                return true;
            }
        }

        mp.put(word, false);
        return false;
    }
}
