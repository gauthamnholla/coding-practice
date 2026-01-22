class Solution {
    public long[] findPrefixScore(int[] nums) {
        long[] result = new long[nums.length];
        long sum = 0;
        long currMax = 0;

        for(int i = 0; i < nums.length; i++) {
            int num = nums[i];
            currMax = Math.max(currMax, num);

            sum += (num + currMax);
            result[i] = sum;
        }

        return result;
    }
}