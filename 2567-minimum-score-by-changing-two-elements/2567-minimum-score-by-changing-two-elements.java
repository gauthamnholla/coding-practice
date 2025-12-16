class Solution {
    public int minimizeSum(int[] nums) {
        
        int max1=0;
        int max2=0;
        int max3=0;
        
        for(int i=0;i<nums.length;i++){
           if(max1<nums[i]){
             max3=max2;
             max2=max1;
             max1=nums[i];
           }else if (max2<nums[i]){
              max3=max2;
              max2=nums[i];
           }else if(max3<nums[i]){
              max3=nums[i];
           }
        }  

        int min1=Integer.MAX_VALUE;
        int min2=Integer.MAX_VALUE;
        int min3=Integer.MAX_VALUE;
        
        for(int i=0;i<nums.length;i++){
           if(min1>nums[i]){
             min3=min2;
             min2=min1;
             min1=nums[i];
           }else if (min2>nums[i]){
              min3=min2;
              min2=nums[i];
           }else if(min3>nums[i]){
              min3=nums[i];
           }
        }
         
          
         int diff1=Math.abs(min3-max1);
         int diff2=Math.abs(max3-min1);
          int diff3=Math.abs(min2-max2);

           int ans=(diff1<diff2)? (diff1<diff3)? diff1 : diff3 :(diff2<diff3)? diff2:  diff3;
      return ans;   
       
    }
}