class Solution {
    public int maximumTop(int[] nums, int k) {
        if (nums.length == 1 && k % 2 == 1) return -1;  
        int i = 0, curmax = 0;
        while (i < nums.length && i < k-1) {
            curmax = Math.max(curmax, nums[i]);
            i++;
        }
        return k < nums.length ? Math.max(curmax, nums[k]) : curmax;
    }
}