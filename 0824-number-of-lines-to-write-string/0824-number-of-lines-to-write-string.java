public class Solution {
    public int[] numberOfLines(int[] widths, String S) {
        int lineCount = 1; // Start with 1 line.
        int currentLineWidth = 0; // Track the current line's width.

        for (char c : S.toCharArray()) {
            int charWidth = widths[c - 'a']; // Get the width of the character (index in 'a' to 'z').
            
            // Check if adding this character exceeds the 100 character limit
            if (currentLineWidth + charWidth > 100) {
                lineCount++; // Start a new line
                currentLineWidth = charWidth; // Start with the current character's width
            } else {
                currentLineWidth += charWidth; // Add this character to the current line
            }
        }

        return new int[] { lineCount, currentLineWidth }; // Return total lines and the width of the last line.
    }
}