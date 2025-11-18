class Solution {
    public int smallestRangeI(int[] nums, int k) {
        int min=nums[0];
        int max=nums[0];
        for(var num:nums)
        {
            min=Math.min(num, min);
            max=Math.max(num, max);
        }
        return Math.max(0, (max-k)-(min+k));
    }
}