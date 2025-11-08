class Solution {

    // Create a palindrome from a given number
    long createPalindrome(long num, boolean odd) {
        long x = num;
        if (odd) x /= 10; // Skip middle digit for odd-length palindrome
        while (x > 0) {
            num = num * 10 + x % 10; // Mirror digits
            x /= 10;
        }
        return num;
    }

    // Check if a number is palindrome in given base 'k'
    boolean isPalindrome(long num, int base) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append((char) (num % base + '0')); // Convert to base 'k'
            num /= base;
        }
        String s = sb.toString();
        int i = 0, j = s.length() - 1;
        while (i < j) if (s.charAt(i++) != s.charAt(j--)) return false;
        return true;
    }

    // Find the sum of first 'n' numbers that are palindromic in both base 10 and base k
    public long kMirror(int k, int n) {
        long sum = 0;
        for (long len = 1; n > 0; len *= 10) { // Generate numbers by increasing length
            // Odd-length palindromes
            for (long i = len; n > 0 && i < len * 10; i++) {
                long p = createPalindrome(i, true);
                if (isPalindrome(p, k)) { sum += p; n--; }
            }
            // Even-length palindromes
            for (long i = len; n > 0 && i < len * 10; i++) {
                long p = createPalindrome(i, false);
                if (isPalindrome(p, k)) { sum += p; n--; }
            }
        }
        return sum; // Return total sum
    }
}
