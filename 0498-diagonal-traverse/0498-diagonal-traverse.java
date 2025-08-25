public class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        // Edge case: if the matrix is empty, return an empty array
        if (matrix == null || matrix.length == 0) return new int[0];

        int m = matrix.length;       // Number of rows
        int n = matrix[0].length;    // Number of columns
        int[] result = new int[m * n]; // Output array of size m*n
        int row = 0, col = 0;        // Start from the top-left corner

        // Loop through each position in the result array
        for (int i = 0; i < m * n; i++) {
            // Place the current element into the result
            result[i] = matrix[row][col];

            // Moving direction depends on the parity of (row + col)
            if ((row + col) % 2 == 0) {
                // Even sum → moving UP-RIGHT
                if (col == n - 1) {      // If we’re at the last column, move down
                    row++;
                } else if (row == 0) {   // If we’re at the first row, move right
                    col++;
                } else {                 // Otherwise, move diagonally up-right
                    row--;
                    col++;
                }
            } else {
                // Odd sum → moving DOWN-LEFT
                if (row == m - 1) {      // If we’re at the last row, move right
                    col++;
                } else if (col == 0) {   // If we’re at the first column, move down
                    row++;
                } else {                 // Otherwise, move diagonally down-left
                    row++;
                    col--;
                }
            }
        }

        // Return the diagonally traversed elements
        return result;
    }
}
