class Solution {
    public long countGoodSubarrays(int[] nums) {
        int n = nums.length;
        
        int[] prevOne = new int[31];
        int[] nextOne = new int[31];
        java.util.Arrays.fill(prevOne, -1);
        java.util.Arrays.fill(nextOne, n);
        
        int[] L = new int[n];
        int[] R = new int[n];
        for (int i = 0; i < n; i++) R[i] = n - 1;
        
        for (int i = 0; i < n; i++) {
            for (int bit = 0; bit < 31; bit++) {
                if ((nums[i] & (1 << bit)) == 0) {
                    L[i] = Math.max(L[i], prevOne[bit] + 1);
                } else {
                    prevOne[bit] = i;
                }
            }
        }
        
        for (int i = n - 1; i >= 0; i--) {
            for (int bit = 0; bit < 31; bit++) {
                if ((nums[i] & (1 << bit)) == 0) {
                    R[i] = Math.min(R[i], nextOne[bit] - 1);
                } else {
                    nextOne[bit] = i;
                }
            }
        }
        
        long ans = 0;
        java.util.HashMap<Integer, Integer> lastIdx = new java.util.HashMap<>();
        
        for (int i = 0; i < n; i++) {
            int l = L[i], r = R[i];
            
            if (lastIdx.containsKey(nums[i])) {
                l = Math.max(l, lastIdx.get(nums[i]) + 1);
            }
            lastIdx.put(nums[i], i);
            
            ans += 1L * (i - l + 1) * (r - i + 1);
        }
        
        return ans;
    }
}