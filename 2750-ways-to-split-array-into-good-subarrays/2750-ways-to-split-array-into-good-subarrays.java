class Solution {
        public int numberOfGoodSubarraySplits(int[] nums) {
        long cnt = 0;
        for (int lo = -1, hi = 0; hi < nums.length; ++hi) {
            if (nums[hi] == 1) {
                if (cnt == 0) {
                    cnt = 1;
                }else {
                    cnt *= hi - lo;
                    cnt %= 1_000_000_007;
                }
                lo = hi;
            }
        }
        return (int)cnt;
    }
}