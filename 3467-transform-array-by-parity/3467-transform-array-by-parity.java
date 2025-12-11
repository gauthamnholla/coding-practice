class Solution {
    public int[] transformArray(int[] nums) {
        int[] res = new int[nums.length];
        int left = 0, right = nums.length - 1;
        for ( int i = 0; i < nums.length; i++) {
            if( nums[i] % 2 == 0 ) {
                res[left] = 0;
                left++;
            }
            else{
                res[right] = 1;
                right--;
            }
        }
        return res;
    }
}