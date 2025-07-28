class Solution {
    public int countMaxOrSubsets(int[] nums) {
        int maxiOr=Integer.MIN_VALUE;
        int c=0;
        int n=nums.length;

        for(int i=1;i<(1<<n);i++)
        {
            int or=0;
            for(int j=0;j<n;j++)
            {
                if((i & (1<<j))!=0)
                {
                    or|=nums[j];
                }
            }
            if(or>maxiOr)
            {
                maxiOr=or;
                c=0;
            }
            if(or==maxiOr)
            {
                c+=1;
            }
        }
        return c;
    }
}