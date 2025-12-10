class Solution {
    public int findNonMinOrMax(int[] nums) {
        if(nums.length < 3){
            return -1;
        }
        int max = Math.max(nums[0], Math.max(nums[1], nums[2]));
        int min = Math.min(nums[0], Math.min(nums[1], nums[2]));
        
        int midElement = (nums[0] + nums[1] + nums[2]) - (max + min);
        return midElement;
    }
}