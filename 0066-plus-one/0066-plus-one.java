class Solution {
    public int[] plusOne(int[] digits) {
        // Traverse digits from right to left
        for (int i = digits.length - 1; i >= 0; i--) {
            // If current digit < 9, just add 1 and return
            if (digits[i] + 1 != 10) {
                digits[i] += 1;
                return digits;
            }
            // If digit becomes 10, set to 0 and carry to next
            digits[i] = 0;
        }

        // If all digits were 9, we need an extra digit at front (e.g., 999 → 1000)
        int[] newDigits = new int[digits.length + 1];
        newDigits[0] = 1;
        return newDigits;
    }
}
