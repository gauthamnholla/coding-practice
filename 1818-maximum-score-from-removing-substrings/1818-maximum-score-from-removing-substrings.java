class Solution {

    
    public int maximumGain(String s, int x, int y) {
        // Ensure "ab" is the higher scoring pair if x > y, otherwise "ba" is.
        // This simplifies the logic by having a consistent order of operations.
        if (x < y) {
            // If y is greater, we can swap the scores and reverse the string.
            // Removing "ba" from the original string is equivalent to removing "ab"
            // from the reversed string.
            int temp = x;
            x = y;
            y = temp;
            s = new StringBuilder(s).reverse().toString();
        }

        // At this point, x >= y, so we always prioritize removing "ab".
        long totalScore = 0;
        
        // Pass 1: Greedily remove the higher-scoring pair "ab"
        StringBuilder sb1 = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == 'b' && sb1.length() > 0 && sb1.charAt(sb1.length() - 1) == 'a') {
                // Found an "ab" pair. Remove 'a' from the end and gain x points.
                sb1.deleteCharAt(sb1.length() - 1);
                totalScore += x;
            } else {
                // No pair found, just append the character.
                sb1.append(c);
            }
        }
        
        // Pass 2: Remove the lower-scoring pair "ba" from the remaining string
        StringBuilder sb2 = new StringBuilder();
        // The string in sb1 is the result after the first pass
        for (char c : sb1.toString().toCharArray()) {
            if (c == 'a' && sb2.length() > 0 && sb2.charAt(sb2.length() - 1) == 'b') {
                // Found a "ba" pair. Remove 'b' from the end and gain y points.
                sb2.deleteCharAt(sb2.length() - 1);
                totalScore += y;
            } else {
                sb2.append(c);
            }
        }
        
        return (int) totalScore;
    }
}
