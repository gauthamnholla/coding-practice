class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        // Case 1: If numerator is 0, the result is always "0"
        if (numerator == 0)
            return "0";
        
        StringBuilder fraction = new StringBuilder();

        // Case 2: Handle sign of result
        // If one is negative and the other is positive, append "-"
        if (numerator < 0 ^ denominator < 0)
            fraction.append("-");        

        // Convert to long and take absolute values (to avoid overflow like -2^31)
        long dividend = Math.abs(Long.valueOf(numerator));
        long divisor = Math.abs(Long.valueOf(denominator));

        // Step 3: Append the integer part (quotient)
        fraction.append(dividend / divisor);

        // Step 4: Get the initial remainder
        long remainder = dividend % divisor;

        // If remainder is 0 → it's an exact division, return the result
        if (remainder == 0) {
            return fraction.toString();
        }

        // Step 5: Otherwise, there is a fractional part → append "."
        fraction.append(".");

        // Map to store remainder → position in the fraction string
        // Used to detect repeating decimals
        Map<Long, Integer> map = new HashMap<>();

        // Step 6: Long division simulation until remainder = 0 or repetition found
        while (remainder != 0) {
            // If we've seen this remainder before, it's a repeating cycle
            if (map.containsKey(remainder)) {
                // Insert '(' at the position where cycle started
                fraction.insert(map.get(remainder), "(");
                // Append ')' at the end
                fraction.append(")");
                break;
            }

            // Record current remainder position
            map.put(remainder, fraction.length());

            // Multiply remainder by 10 (like manual long division)
            remainder *= 10;

            // Append the next digit of quotient
            fraction.append(remainder / divisor);

            // Update remainder
            remainder %= divisor;
        }

        // Step 7: Return the final result
        return fraction.toString();
    }
}
