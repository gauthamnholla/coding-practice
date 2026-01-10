class Solution {
    /**
     * Time Complexity: O(n) where n is the length of string s
     * - We iterate through the string twice, each taking O(n) time
     * 
     * Space Complexity: O(1)
     * - We only use a constant amount of extra space regardless of input size
     * - No additional data structures that scale with input
     */
    public int minFlipsMonoIncr(String s) {
        int zeroes = 0;
        int ones = 0;
        
        // First pass: count total zeroes
        for(char ch : s.toCharArray()){
            if(ch == '0'){
                zeroes++;
            }
        }
        
        int output = zeroes; // Initialize with flipping all zeroes to ones
        
        // Second pass: find minimum flips needed
        for(char ch : s.toCharArray()){
            if(ch == '0'){
                zeroes--; // One less zero to flip if we include this position in prefix
            }
            else if(ch == '1'){
                ones++; // Need to flip this one to zero if in prefix
            }
            output = Math.min(output, zeroes+ones);
        }
        
        return output;
    }
}