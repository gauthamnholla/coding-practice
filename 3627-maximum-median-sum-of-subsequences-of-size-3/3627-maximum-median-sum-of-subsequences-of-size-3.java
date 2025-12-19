class Solution {
    public long maximumMedianSum(int[] nums) {
        Arrays.sort(nums);
        int left=0;
        long res=0;
        for(int i=nums.length-2;i>left;i-=2){
            res+=nums[i];
            left++;
        }
        return res;
    }
}