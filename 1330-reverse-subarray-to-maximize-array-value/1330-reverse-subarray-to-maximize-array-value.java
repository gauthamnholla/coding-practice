class Solution {
        public int maxValueAfterReverse(int[] nums) {
        int valWithoutReverse = 0, improvementByReversing = 0, secondMin = Integer.MAX_VALUE, secondMax = Integer.MIN_VALUE, n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            int currDiff = Math.abs(nums[i] - nums[i + 1]);
            valWithoutReverse += currDiff;
            improvementByReversing = Math.max(improvementByReversing, Math.abs(nums[i + 1] - nums[0]) - currDiff); // Reverse nums[0] to nums[i]
            improvementByReversing = Math.max(improvementByReversing, Math.abs(nums[i] - nums[n - 1]) - currDiff); // Reverse nums[i+1] to nums[n-1]
            secondMin = Math.min(secondMin, Math.max(nums[i], nums[i + 1])); // Keep track of the second min
            secondMax = Math.max(secondMax, Math.min(nums[i], nums[i + 1])); // Keep track of the second max
        } // The last possible improvementByReversing is reversing [secondMin...secondMax] in: ... firstMin [secondMin ... secondMax] firstMax ...
        return valWithoutReverse + Math.max(improvementByReversing, 2 * (secondMax - secondMin)); // 2*(secondMax-secondMin) could be achieved by reversing [secondMin...secondMax]
    }
}