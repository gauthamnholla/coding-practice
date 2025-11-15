class Solution {
    public int[][] imageSmoother(int[][] img) {
        int res[][] = new int[img.length][img[0].length];

        // Go through each pixel in the image
        for (int i = 0; i < img.length; i++) {
            for (int j = 0; j < img[0].length; j++) {
                // Compute smoothed value for each pixel
                res[i][j] = smoothen(img, i, j);
            }
        }
        return res;
    }
    
    // Helper function to average surrounding cells (3x3 grid)
    int smoothen(int[][] img, int x, int y) {
        int m = img.length; 
        int n = img[0].length;
        int sum = 0, count = 0;

        // Check 8 neighbors + itself
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nx = x + i;
                int ny = y + j;

                // Skip out-of-bound cells
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;

                sum += img[nx][ny];
                count++;
            }
        }
        // Return the average of valid surrounding cells
        return sum / count;
    }
}
