class Solution {

    public boolean isValidPlacement(int[] bottom, int[] top){
        // To check if top cuboid can be placed on bottom cuboid or not
        if(bottom[0]>=top[0] && bottom[1]>=top[1] && bottom[2]>=top[2]){
            return true;
        }
        return false;
    }

    // find the maximum stack height using DP (variation of LIS)
    public int maxLIS(int[][] cuboids){
        // Using Optimized DP Approach (1D Tabulation)
        int n = cuboids.length;
        int[] dp = new int[n]; // dp[i] stores max height of stack ending at cuboid i
        
        // Initialize dp: each cuboid itself is a stack of its own height
        for(int i=0; i<n; i++){
            dp[i] = cuboids[i][2];
        }

        int longestHeight = dp[0]; // initialize longest height as first cuboid height

        for(int i=1; i<n; i++){
            for(int j=0; j<i; j++){
                if(isValidPlacement(cuboids[j], cuboids[i])){ 
                    // If cuboid i can be placed on cuboid j
                    dp[i] = Math.max(dp[i], dp[j] + cuboids[i][2]); 
                    // update dp[i] -> either keep its own height or
                    // stack it over cuboid j to get a higher stack
                }
            }
            longestHeight = Math.max(longestHeight, dp[i]); // Update the overall longest height
        }
        return longestHeight; // maximum stack height
    }

    public int maxHeight(int[][] cuboids) {
        // Variation of LIS - Longest Increasing Subsequence

        // Step 1: Sort each cuboid's dimensions as rotation allowed
        for(int i=0; i<cuboids.length; i++){
            Arrays.sort(cuboids[i]);
        }

        // Step 2: Sort cuboids to convert stacking problem into LIS
        Arrays.sort(cuboids, (a, b) -> {
            if(a[0] != b[0]) return b[0] - a[0]; // Sort by first dimension descending
            if(a[1] != b[1]) return b[1] - a[1]; // Then by second dimension descending  
            return b[2] - a[2]; // Then by third dimension descending
        });

        // Now this problem is converted to LIS problem.
        // Find LIS for heights
        return maxLIS(cuboids);
    }
}