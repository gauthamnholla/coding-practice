class Solution {
    public int totalHammingDistance(int[] nums) {
        int n = nums.length;
        int total = 0;
        for (int bit = 0; bit < 32; bit++) {
            int ones = 0;
            for (int num : nums) {
                if ((num & (1 << bit)) != 0) {
                    ones++;
                }
            }
            int zeros = n - ones;
            total += ones * zeros; 
        }

        return total;
    }
}