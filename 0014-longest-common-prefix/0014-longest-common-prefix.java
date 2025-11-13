class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return ""; // Handle empty input

        String pref = strs[0];        // Take first string as initial prefix
        int prefLen = pref.length();  // Length of current prefix

        // Compare prefix with each string
        for (int i = 1; i < strs.length; i++) {
            String s = strs[i];

            // Reduce prefix until it matches the start of the current string
            while (prefLen > s.length() || !pref.equals(s.substring(0, prefLen))) {
                prefLen--;
                if (prefLen == 0) return ""; // No common prefix
                pref = pref.substring(0, prefLen);
            }
        }

        return pref; // Return final common prefix
    }
}
