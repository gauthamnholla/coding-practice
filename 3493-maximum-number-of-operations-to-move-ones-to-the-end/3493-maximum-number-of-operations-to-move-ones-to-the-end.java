class Solution {
    public int maxOperations(String s) {
        int ones = 0, res = 0;

        // Traverse the string
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1')
                ones++; // Count number of '1's seen so far
            else if (i > 0 && s.charAt(i - 1) == '1')
                res += ones; // Each "10" contributes to operations
        }

        return res; // Return total number of operations
    }
}
