class Solution {
    public int[] sortedSquares(int[] nums) {
        int len = nums.length;

        int l = 0 , r = len-1 , idx = len-1;
        int[] res = new int[len];
        while(l<=r)
        {
            int leftSq = nums[l] * nums[l];
            int rightSq = nums[r] * nums[r];
            if(leftSq > rightSq)
            {
                res[idx--] = leftSq;
                l++;
            }

            else 
            {
                res[idx--] = rightSq;
                r--;
            }
        }

        return res;
    }
}