class Solution {
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums); // Sort the array

        // Case 1: Two smallest (possibly negative) * largest
        int a = nums[0] * nums[1] * nums[nums.length - 1];

        // Case 2: Three largest numbers
        int b = nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3];

        // Return the larger product
        return (a > b) ? a : b;
    }
}
