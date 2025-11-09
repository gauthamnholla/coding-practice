class Solution {
    public int countOperations(int num1, int num2) {
        int res = 0; // Count total operations
        
        // Continue until one number becomes 0
        while (num1 != 0 && num2 != 0) {
            res += num1 / num2; // Count how many times num2 fits in num1
            num1 %= num2;       // Update num1 with remainder

            // Swap numbers for next step
            int temp = num1;
            num1 = num2;
            num2 = temp;
        }

        return res; // Return total count
    }
}
