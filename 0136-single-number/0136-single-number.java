class Solution {
    public int singleNumber(int[] nums) {
        int res = 0;

        // XOR all numbers — duplicates cancel out, leaving the single number
        for (int n : nums) {
            res ^= n;
        }

        return res; // The unique element
    }
}
