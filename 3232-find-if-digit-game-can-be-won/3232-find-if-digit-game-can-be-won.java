class Solution {
    public boolean canAliceWin(int[] nums) {
        int singleDigit = 0, doubleDigit = 0;

        for(int i=0; i<nums.length; i++){
            if(nums[i] <= 9){
                singleDigit += nums[i];
            }else{
                doubleDigit += nums[i];
            }
        }

        return (singleDigit > doubleDigit) || (singleDigit < doubleDigit);
    }
}