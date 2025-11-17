class Solution {
    public int dominantIndex(int[] nums) {
        int n = nums.length;
        int[] original = Arrays.copyOf(nums, n);
        Arrays.sort(nums);
        if(nums[n-1] >= (2*nums[n-2]))  
            for(int i=0;i<n;i++) if(original[i] == nums[n-1]) return i;
        return -1;
    }
}