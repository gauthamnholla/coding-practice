class Solution {
    public int getMinDistance(int[] nums, int target, int start) {
        int right = start;
        int left = start - 1;

        while (left >= 0 || right < nums.length) {

            if (right < nums.length && nums[right] == target) {
                return right - start;
            }

            if (left >= 0 && nums[left] == target) {
                return start - left;
            }

            right++;
            left--;
        }

        return -1;

    }
}