class Solution {
    public int centeredSubarrays(int[] nums) {
        int count = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                for (int k = i; k <= j; k++) {
                    if (nums[k] == sum) { count++; break; }
                }
            }
        }

        return count;
    }
}