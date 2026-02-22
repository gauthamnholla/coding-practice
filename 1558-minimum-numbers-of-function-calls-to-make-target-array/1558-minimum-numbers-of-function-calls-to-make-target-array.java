class Solution {
    public int minOperations(int[] nums) {
        //make everyone even and divide;
        int ans =0;
        int n= nums.length;
        while(true){
            boolean greater=false;
            for(int i=0;i<n;i++){
                if(nums[i]%2!=0){nums[i]--;ans++;}
            }
            for(int i=0;i<n;i++){
                nums[i]/=2;
                if(nums[i]>0)greater=true;
            }
            ans++;
            if(!greater)break;
        }
        return ans-1;// -1 for the all zero case
    }
}