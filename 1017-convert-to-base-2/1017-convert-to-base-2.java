class Solution {
    public String baseNeg2(int n) {
        // Base case: if number is zero, return "0"
        if (n == 0) return "0";

        StringBuilder sb = new StringBuilder();

        // Repeat until number reduces to zero
        while (n != 0) {
            // Get remainder when dividing by -2
            int remainder = n % -2;
            int quotient = n / -2;

            // If remainder is negative, fix it
            if (remainder < 0) {
                remainder += 2;   // make remainder positive (0 or 1)
                quotient += 1;    // adjust quotient to maintain correctness
            }

            // Append remainder (build number in reverse)
            sb.append(remainder);

            // Move to next quotient
            n = quotient;
        }

        // Reverse the string to get correct base -2 representation
        return sb.reverse().toString();
    }
}