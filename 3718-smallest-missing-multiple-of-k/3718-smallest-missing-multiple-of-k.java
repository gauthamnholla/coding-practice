class Solution {
    public int missingMultiple(int[] nums, int k) {
        Arrays.sort(nums);
        int res = k;
        for (int num : nums)
            if (num == res) res += k;
        return res;
    }
}