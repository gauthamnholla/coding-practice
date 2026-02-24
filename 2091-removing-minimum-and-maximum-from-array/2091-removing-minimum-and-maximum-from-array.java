class Solution {
    public int minimumDeletions(int[] nums) {
        
        int maxIndex = 0;
        int minIndex = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        // Step 1: Find min & max index
        for(int i = 0; i < nums.length; i++){
            int n = nums[i];

            if(min > n){
                minIndex = i;
                min = n;
            }

            if(max < n){
                maxIndex = i;
                max = n;
            }
        }

        int n = nums.length;

        // Case 3: both sides
        int case3 =
            Math.min(minIndex, maxIndex) + 1
            +
            (n - Math.max(minIndex, maxIndex));

        // Case 1: only front
        int case1 =
            Math.max(minIndex, maxIndex) + 1;

        // Case 2: only back
        int case2 =
            n - Math.min(minIndex, maxIndex);

        return Math.min(case3,
                Math.min(case1, case2));
    }
}