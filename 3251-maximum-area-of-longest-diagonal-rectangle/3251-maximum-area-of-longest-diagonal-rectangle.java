public class Solution {
    public int areaOfMaxDiagonal(int[][] dimensions) {
        int bestDiag2 = -1;   // Stores the maximum diagonal squared value found so far
        int bestArea = 0;     // Stores the area corresponding to the maximum diagonal

        // Loop through each rectangle (length, width pair)
        for (int[] d : dimensions) {
            int L = d[0], W = d[1]; // Extract length and width from current rectangle

            // Calculate diagonal squared (L^2 + W^2) to avoid using sqrt
            int curDiag2 = L * L + W * W;

            // Calculate area of current rectangle
            int curArea  = L * W;

            // Update if current diagonal is greater than best so far
            if (curDiag2 > bestDiag2) {
                bestDiag2 = curDiag2;
                bestArea = curArea;

            // If diagonals are equal, choose the rectangle with larger area
            } else if (curDiag2 == bestDiag2) {
                if (curArea > bestArea) bestArea = curArea;
            }
        }

        // Return the area of the rectangle with the longest diagonal
        return bestArea;
    }
}
