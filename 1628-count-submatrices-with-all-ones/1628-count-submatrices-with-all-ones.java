class Solution {
    public int numSubmat(int[][] mat) {
        int m = mat.length, n = mat[0].length;   // m = number of rows, n = number of columns
        int[] h = new int[n];                    // array to store histogram heights for each column
        int cnt = 0;                             // total count of submatrices with all 1s

        // Iterate over each row
        for(int i = 0; i < m; i++) {
            // Update histogram heights for the current row
            for(int j = 0; j < n; j++) {
                h[j] = mat[i][j] == 1 ? h[j] + 1 : 0; 
                // If current cell is 1, increase height; else reset to 0
            }

            // Count submatrices ending at row i using histogram heights
            for(int j = 0; j < n; j++) {
                int mn = h[j];  // minimum height for the current range (start from column j)
                
                // Expand leftwards from column j to 0
                for(int k = j; k >= 0 && mn > 0; k--) {
                    mn = Math.min(mn, h[k]);   // maintain minimum height in the range
                    cnt += mn;                 // add rectangles formed with height mn
                }
            }
        }
        return cnt;  // return total count of submatrices
    }
}
