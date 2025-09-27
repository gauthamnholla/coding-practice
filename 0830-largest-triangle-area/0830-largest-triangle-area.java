class Solution {
    public double largestTriangleArea(int[][] points) {
        // Step 1 - Initialize a variable to store the maximum area found so far
        double maxArea = 0.0;

        // Step 2 - Get the number of points
        int n = points.length;

        // Step 3 - Use three nested loops to generate all unique combinations of 3 points
        // i < j < k ensures no repetition and order doesn't matter
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    
                    // Extract coordinates of three points
                    int x1 = points[i][0];
                    int y1 = points[i][1];
                    int x2 = points[j][0];
                    int y2 = points[j][1];
                    int x3 = points[k][0];
                    int y3 = points[k][1];

                    // Step 4 - Calculate the area of the triangle using the shoelace (cross product) formula
                    // Formula: Area = 0.5 * | x1*(y2 - y3) + x2*(y3 - y1) + x3*(y1 - y2) |
                    double area = 0.5 * Math.abs(
                        x1 * (y2 - y3) + 
                        x2 * (y3 - y1) + 
                        x3 * (y1 - y2)
                    );

                    // Step 5 - Update maxArea if this triangle has a larger area
                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        // Step 6 - Return the maximum area found
        return maxArea;
    }
}
