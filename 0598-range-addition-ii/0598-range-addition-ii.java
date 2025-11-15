class Solution {
    public int maxCount(int m, int n, int[][] ops) {
        int min_row = m; // Track smallest affected row range
        int min_col = n; // Track smallest affected column range

        // Each operation defines a rectangle from (0,0) to (a,b)
        // Find the intersection of all such rectangles
        for (int i = 0; i < ops.length; i++) {
            if (ops[i][0] < min_row) min_row = ops[i][0];
            if (ops[i][1] < min_col) min_col = ops[i][1];
        }

        // The number of max-value cells = area of smallest overlapping rectangle
        return min_row * min_col;
    }
}
