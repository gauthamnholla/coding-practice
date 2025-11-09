class Solution {
    public int possibleStringCount(String word) {
        int n = word.length();
        int count = n; // Start with total length

        // Check for consecutive identical characters
        for (int i = 1; i < n; i++) {
            // If character changes, reduce count
            if (word.charAt(i) != word.charAt(i - 1)) {
                count--;
            }
        }

        return count; // Return possible string count
    }
}
