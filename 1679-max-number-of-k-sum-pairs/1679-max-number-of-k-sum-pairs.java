class Solution {
    public int maxOperations(int[] nums, int k) {
       Arrays.sort(nums);
       int n=nums.length;
       int start=0;
       int end=n-1;
       int counter=0;
        while(start<end)
        {
            if(nums[start]+nums[end]==k)
            {
                start++;
                end--;
                counter++;
            }
            else if(nums[start]+nums[end]<k)
            start++;
            else
            end--;
        }
        return counter;
    }
}