class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {

        // Check every element except first row/column
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {

                // Each element must match the one diagonally up-left
                if (matrix[i][j] != matrix[i - 1][j - 1]) {
                    return false;
                }
            }
        }

        return true; // All diagonals match
    }
}
