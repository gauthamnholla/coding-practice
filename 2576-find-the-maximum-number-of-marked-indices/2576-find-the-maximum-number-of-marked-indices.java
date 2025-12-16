class Solution {
    public int maxNumOfMarkedIndices(int[] nums) {
    int i = 0, n = nums.length;
    Arrays.sort(nums);
    for (int j = n - n / 2; j < n; ++j)
        i += 2 * nums[i] <= nums[j] ? 1 : 0;
    return i * 2;
}
}