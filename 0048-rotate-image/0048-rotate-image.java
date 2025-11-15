class Solution {
    public void rotate(int[][] matrix) {
        int edgeLength = matrix.length;

        int top = 0;
        int bottom = edgeLength - 1;

        // Step 1: Reverse the matrix vertically (top ↔ bottom)
        while (top < bottom) {
            for (int col = 0; col < edgeLength; col++) {
                int temp = matrix[top][col];
                matrix[top][col] = matrix[bottom][col];
                matrix[bottom][col] = temp;
            }
            top++;
            bottom--;
        }

        // Step 2: Transpose the matrix (swap across diagonal)
        for (int row = 0; row < edgeLength; row++) {
            for (int col = row + 1; col < edgeLength; col++) {
                int temp = matrix[row][col];
                matrix[row][col] = matrix[col][row];
                matrix[col][row] = temp;
            }
        }
    }
}
