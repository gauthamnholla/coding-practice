// Java program to sort the diagonals of a matrix
import java.util.*;

class Solution {
    public int[][] sortMatrix(int[][] matrix) {
        // Map to store each diagonal using (i - j) as the key
        // Above main diagonal -> ascending order
        // Main diagonal & below -> descending order
        Map<Integer, PriorityQueue<Integer>> diagonalMap = new HashMap<>();
        int rows = matrix.length, cols = matrix[0].length;

        // Step 1: Group elements of the matrix by their diagonals
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int key = i - j; // unique key for each diagonal
                // If diagonal not present, add PQ:
                //   ascending if key < 0
                //   descending if key >= 0
                diagonalMap.putIfAbsent(key, key < 0 ? new PriorityQueue<>()
                                                     : new PriorityQueue<>(Collections.reverseOrder()));
                // Add element to the respective diagonal PQ
                diagonalMap.get(key).offer(matrix[i][j]);
            }
        }

        // Step 2: Place back the sorted values from each diagonal
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int key = i - j; // get the diagonal key
                // Poll the next sorted element and assign it back to matrix
                matrix[i][j] = diagonalMap.get(key).poll();
            }
        }

        // Return the sorted matrix
        return matrix;
    }
}
