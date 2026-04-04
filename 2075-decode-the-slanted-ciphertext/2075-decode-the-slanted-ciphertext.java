class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        if (rows == 1) return encodedText;
        
        int n = encodedText.length();
        int cols = n / rows;
        
        StringBuilder result = new StringBuilder();
        
        for (int startCol = 0; startCol < cols; startCol++) {
            int r = 0, c = startCol;
            while (r < rows && c < cols) {
                result.append(encodedText.charAt(r * cols + c));
                r++;
                c++;
            }
        }
        
        // Remove trailing spaces
        int end = result.length() - 1;
        while (end >= 0 && result.charAt(end) == ' ') end--;
        
        return result.substring(0, end + 1);
    }
}