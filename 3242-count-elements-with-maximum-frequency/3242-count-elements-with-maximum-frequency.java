class Solution {
    public int maxFrequencyElements(int[] nums) {
        // Frequency array (values are limited 1–100 by problem constraint)
        byte[] freq = new byte[101];

        // max = current highest frequency found
        // res = total count of elements that appear with this max frequency
        byte max = 0, res = 0;

        // Count frequencies
        for (int n : nums) {
            // Increase the frequency of this number
            byte f = ++freq[n];

            if (f > max) {
                // Found a new maximum frequency
                max = f;
                res = f;  // reset result to this frequency
            } else if (f == max) {
                // Another number reached the same maximum frequency
                res += f; // add its contribution
            }
        }
        return res;
    }
}
