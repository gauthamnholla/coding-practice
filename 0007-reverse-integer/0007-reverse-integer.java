class Solution {
   
    public int reverse(int x) {
        int reversed = 0;

        while (x != 0) {
            // Get the last digit of x
            int pop = x % 10;
            // Remove the last digit from x
            x /= 10;

            // --- Overflow Check ---
            // This must be done BEFORE we multiply 'reversed' by 10.

            // Check for positive overflow
            // Integer.MAX_VALUE is 2,147,483,647
            if (reversed > Integer.MAX_VALUE / 10 || (reversed == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }

            // Check for negative overflow
            // Integer.MIN_VALUE is -2,147,483,648
            if (reversed < Integer.MIN_VALUE / 10 || (reversed == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            
            // If it's safe, append the digit to the reversed number
            reversed = reversed * 10 + pop;
        }

        return reversed;
    }
}
