class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> ans = new HashMap<>();

        // Step 1: Process each string
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);             // Sort characters to form key
            String key = new String(chars); // Same anagrams will have same key

            // Step 2: Add word to its anagram group
            if (!ans.containsKey(key)) {
                ans.put(key, new ArrayList<>());
            }
            ans.get(key).add(s);
        }

        // Step 3: Return all grouped anagrams
        return new ArrayList<>(ans.values());
    }
}
