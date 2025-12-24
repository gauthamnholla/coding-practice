class Solution {
   public int arrayNesting(int[] nums) {
    boolean[] v = new boolean[nums.length];
    int ans = 0;
    for (int i = 0; i < nums.length; i++) {
        if (!v[i]) {
            int c = 0;
            int j = i;
            while (!v[j]) {
                c++;
                v[j] = true;
                j = nums[j];
            }
            ans = Math.max(ans, c);
        }
    }
    return ans;
}
}