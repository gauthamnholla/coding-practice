class Solution {
    public int maxAbsoluteSum(int[] nums) {
        int result = 0;
        int maxsum = 0;
        int minsum = 0;
        for(int i=0; i < nums.length ; i++){
            maxsum = Math.max(maxsum + nums[i] , nums[i]);

            minsum = Math.min(minsum + nums[i] , nums[i]);

            result = Math.max(result, Math.max(Math.abs(minsum),maxsum));
        }
        return result;
    }
}