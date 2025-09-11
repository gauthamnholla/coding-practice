class Solution {
    public String sortVowels(String s) {
        // Step 1: Collect all vowels from the string
        List<Character> vowels = new ArrayList<>();
        for (char c : s.toCharArray()) {
            if ("AEIOUaeiou".indexOf(c) != -1) { // Check if c is a vowel
                vowels.add(c);
            }
        }

        // Step 2: Sort the vowels in ascending order
        Collections.sort(vowels);

        // Step 3: Rebuild the string with vowels replaced in sorted order
        StringBuilder result = new StringBuilder();
        int vIndex = 0; // Pointer for sorted vowels
        for (char c : s.toCharArray()) {
            if ("AEIOUaeiou".indexOf(c) != -1) {
                // Replace vowel with next one from sorted list
                result.append(vowels.get(vIndex++));
            } else {
                // Keep consonants unchanged
                result.append(c);
            }
        }

        // Step 4: Return the new string with sorted vowels
        return result.toString();
    }
}
