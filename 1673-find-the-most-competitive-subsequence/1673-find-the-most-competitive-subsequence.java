class Solution {
    public int[] mostCompetitive(int[] nums, int k) {
        int n = nums.length;
        int toRemove = n - k;
        int[] stack = new int[k];
        int top = -1;

        for (int num : nums) {
            while (top >= 0 && stack[top] > num && toRemove > 0) {
                top--;
                toRemove--;
            }
            if (top + 1 < k) {
                stack[++top] = num;
            } else {
                toRemove--;
            }
        }
        return stack;
    }
}
