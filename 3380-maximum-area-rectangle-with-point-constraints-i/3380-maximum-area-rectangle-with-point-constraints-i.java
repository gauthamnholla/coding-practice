class Solution {
    public int maxRectangleArea(int[][] points) {
        // Step 1: Store points in a HashSet for fast lookup
        HashSet<String> pointSet = new HashSet<>();
        int n = points.length;

        for (int i = 0; i < n; i++) {
            pointSet.add(points[i][0] + "," + points[i][1]);
        }

        long maxArea = -1;

        // Step 2: Check all pairs of points to form rectangles
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int x1 = points[i][0], y1 = points[i][1];
                int x2 = points[j][0], y2 = points[j][1];

                // Step 3: Ensure the points are diagonally opposite
                if (x1 != x2 && y1 != y2) {
                    // Step 4: Check if the other two corners exist
                    if (pointSet.contains(x1 + "," + y2) && pointSet.contains(x2 + "," + y1)) {
                        // Calculate the area of the rectangle
                        long area = (long) Math.abs(x2 - x1) * Math.abs(y2 - y1);

                        // Step 5: Ensure that no other points lie inside the rectangle
                        boolean validRectangle = true;
                        for (int k = 0; k < n; k++) {
                            int x = points[k][0], y = points[k][1];

                            // Skip the four corners of the rectangle
                            if ((x == x1 || x == x2) && (y == y1 || y == y2)) {
                                continue;
                            }

                            // Check if the point lies inside the rectangle
                            if (x > Math.min(x1, x2) && x < Math.max(x1, x2) && y > Math.min(y1, y2) && y < Math.max(y1, y2)) {
                                validRectangle = false;
                                break;
                            }

                            // Ensure no points lie on the edges of the rectangle
                            if ((x == x1 || x == x2) && (y >= Math.min(y1, y2) && y <= Math.max(y1, y2))) {
                                validRectangle = false;
                                break;
                            }
                            if ((y == y1 || y == y2) && (x >= Math.min(x1, x2) && x <= Math.max(x1, x2))) {
                                validRectangle = false;
                                break;
                            }
                        }
                        if (validRectangle) {
                            maxArea = Math.max(maxArea, area);
                        }
                    }
                }
            }
        }
        return (int) maxArea;
    }
}
