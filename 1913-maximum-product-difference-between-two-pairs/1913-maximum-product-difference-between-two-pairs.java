class Solution {
    public int maxProductDifference(int[] nums) {
        Arrays.sort(nums);
        int len=nums.length;
        int res=(nums[len-1]*nums[len-2])-(nums[0]*nums[1]);
        return res;
    }
}