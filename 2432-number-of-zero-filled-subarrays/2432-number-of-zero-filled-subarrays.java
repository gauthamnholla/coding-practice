public class Solution {
    public long zeroFilledSubarray(int[] nums) {
        long ans = 0;
        long run = 0; // current consecutive zeros length
        for (int x : nums) {
            if (x == 0) {
                run++;
                ans += run; // each new zero extends all previous zero subarrays by 1 and adds a new [0]
            } else {
                run = 0;
            }
        }
        return ans;
    }
}
