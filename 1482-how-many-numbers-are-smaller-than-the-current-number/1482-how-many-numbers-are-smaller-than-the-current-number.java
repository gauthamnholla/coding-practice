class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] freq = new int[101];
        
        // Step 1: Count frequency of each number
        for (int num : nums) {
            freq[num]++;
        }
        
        // Step 2: Prefix sum → freq[i] now stores how many numbers are < i
        for (int i = 1; i < 101; i++) {
            freq[i] += freq[i - 1];
        }
        
        // Step 3: Build result
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            ans[i] = num == 0 ? 0 : freq[num - 1];
        }
        
        return ans;
    }
}
