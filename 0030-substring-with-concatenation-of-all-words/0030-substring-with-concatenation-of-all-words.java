class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();

        // Edge case: empty input
        if (words.length == 0 || s.length() == 0) return ans;

        int wordSize = words[0].length();   // Each word length
        int wordCount = words.length;       // Total number of words
        int N = s.length();

        // Count frequency of each word
        HashMap<String, Integer> originalCount = new HashMap<>();
        for (String word : words) {
            originalCount.put(word, originalCount.getOrDefault(word, 0) + 1);
        }

        // Try all possible starting offsets (within word length)
        for (int offset = 0; offset < wordSize; offset++) {
            HashMap<String, Integer> currentCount = new HashMap<>();
            int start = offset;
            int count = 0;

            // Slide window by wordSize each time
            for (int end = offset; end + wordSize <= N; end += wordSize) {
                String currWord = s.substring(end, end + wordSize);

                if (originalCount.containsKey(currWord)) {
                    // Add current word count
                    currentCount.put(currWord, currentCount.getOrDefault(currWord, 0) + 1);
                    count++;

                    // If word count exceeds original, shrink from left
                    while (currentCount.get(currWord) > originalCount.get(currWord)) {
                        String startWord = s.substring(start, start + wordSize);
                        currentCount.put(startWord, currentCount.get(startWord) - 1);
                        start += wordSize;
                        count--;
                    }

                    // If all words match, record starting index
                    if (count == wordCount) ans.add(start);

                } else {
                    // Reset window if invalid word
                    currentCount.clear();
                    count = 0;
                    start = end + wordSize;
                }
            }
        }

        return ans;
    }
}
