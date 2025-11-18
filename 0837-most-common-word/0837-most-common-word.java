class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        // Normalize to lowercase
        String low = paragraph.toLowerCase();

        // Split into words by non-letters
        String[] words = low.split("[^a-z]+");

        Map<String, Integer> map = new HashMap<>();
        String res = "";

        // Add banned words to a fast lookup set
        Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));

        // Count frequencies of non-banned words
        for (String word : words) {
            if (word.isEmpty() || bannedSet.contains(word)) continue;
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        // Find the most frequent valid word
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                res = entry.getKey();
            }
        }

        return res;
    }
}
