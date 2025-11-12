class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();

        // If input is empty, return empty list
        if (digits == null || digits.length() == 0) {
            return res;
        }

        // Map digits to their corresponding letters
        Map<Character, String> digitToLetters = new HashMap<>();
        digitToLetters.put('2', "abc");
        digitToLetters.put('3', "def");
        digitToLetters.put('4', "ghi");
        digitToLetters.put('5', "jkl");
        digitToLetters.put('6', "mno");
        digitToLetters.put('7', "pqrs");
        digitToLetters.put('8', "tuv");
        digitToLetters.put('9', "wxyz");

        // Start backtracking from index 0
        backtrack(digits, 0, new StringBuilder(), res, digitToLetters);

        return res; // Return all combinations
    }

    // Helper function for backtracking
    private void backtrack(String digits, int idx, StringBuilder comb, List<String> res, Map<Character, String> digitToLetters) {
        // Base case: full combination formed
        if (idx == digits.length()) {
            res.add(comb.toString());
            return;
        }

        // Get letters for current digit
        String letters = digitToLetters.get(digits.charAt(idx));

        // Try each letter and backtrack
        for (char letter : letters.toCharArray()) {
            comb.append(letter); // Choose
            backtrack(digits, idx + 1, comb, res, digitToLetters); // Explore
            comb.deleteCharAt(comb.length() - 1); // Unchoose
        }
    }
}
