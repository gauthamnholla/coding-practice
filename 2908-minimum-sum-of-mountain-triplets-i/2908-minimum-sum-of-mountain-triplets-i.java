class Solution {
    public int minimumSum(int[] nums) {
        int n = nums.length;

        int[] prefixMin = new int[n];
        prefixMin[0] = Integer.MAX_VALUE;
        int minElement = nums[0];
        for (int i = 1; i < n; i++) {
            prefixMin[i] = Math.min(nums[i-1], minElement);
            if (prefixMin[i] > nums[i])
                prefixMin[i] = Integer.MAX_VALUE;
            
            minElement = Math.min(nums[i], minElement);
        }

        int[] suffixMin = new int[n];
        suffixMin[n - 1] = Integer.MAX_VALUE;
        minElement = nums[n-1];
        for (int i = n - 2; i >= 0; --i) {
            suffixMin[i] = Math.min(nums[i+1], minElement);
            if (suffixMin[i] > nums[i])
                suffixMin[i] = Integer.MAX_VALUE;
            
            minElement = Math.min(nums[i], minElement);
        }

        int ans = Integer.MAX_VALUE;
        for (int j = 1; j < n - 1; j++) {
            // nums[i] = prefixMin[j], nums[k] = suffixMin[j]

            // checking if nums[j] is peak of mountain triplet
            if (prefixMin[j] < nums[j] && nums[j] > suffixMin[j]) {
                int tripletSum = prefixMin[j] + nums[j] + suffixMin[j];
                ans = Math.min(ans, tripletSum);
            }           
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}