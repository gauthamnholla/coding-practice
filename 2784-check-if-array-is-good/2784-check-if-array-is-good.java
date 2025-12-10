class Solution {
    public boolean isGood(int[] nums) {
        int n = nums.length-1;
        int highest = 0;
        int[] f = new int[nums.length];
        for(int i = 0; i<nums.length; i++) {    
            if(nums[i] > n) return false;
            f[nums[i]]++;
            if(nums[i] > highest) {
                highest = nums[i];
            } 
        }
        for(int i = 1; i<n; i++) {
            if(f[i] != 1) return false;
        }
        if(f[n] != 2) return false;
        return true;
    }
}