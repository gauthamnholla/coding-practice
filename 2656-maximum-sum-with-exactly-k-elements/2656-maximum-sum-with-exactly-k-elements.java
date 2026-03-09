class Solution {
    public int maximizeSum(int[] nums, int k) { 
        Arrays.sort(nums);
        int sum=0;
       
          int ans=nums[nums.length-1];
        while(k!=0){
          
            sum+=ans;
        
          
            k--;
            ans=ans+1;
        }
        return sum;
    }
}