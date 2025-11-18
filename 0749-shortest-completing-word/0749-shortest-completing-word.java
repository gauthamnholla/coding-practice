class Solution {
    public String shortestCompletingWord(String licensePlate, String[] words) {

        // Keep only letters from licensePlate and convert to lowercase
        String str = licensePlate.replaceAll("[^A-Za-z]", "").toLowerCase();

        // Count needed characters
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : str.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        String shortest = null;

        // Check each word
        for (String word : words) {
            Map<Character, Integer> wordMap = new HashMap<>();

            // Count characters in the word
            for (char ch : word.toLowerCase().toCharArray()) {
                wordMap.put(ch, wordMap.getOrDefault(ch, 0) + 1);
            }

            // Check if word covers all required characters
            boolean valid = true;
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                if (wordMap.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                    valid = false;
                    break;
                }
            }

            // Choose shortest valid word
            if (valid && (shortest == null || word.length() < shortest.length())) {
                shortest = word;
            }
        }

        return shortest;
    }
}
