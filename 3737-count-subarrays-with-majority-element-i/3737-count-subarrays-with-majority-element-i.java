class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n=nums.length;
        int res=0;
        for(int i=0;i<n;i++){
            int tct=0;
            int other=0;
            for(int j=i;j<n;j++){
                if(nums[j]==target)tct++;
                else other++;

                if(tct>other)res++;
            }
        }
        return res;
    }
}