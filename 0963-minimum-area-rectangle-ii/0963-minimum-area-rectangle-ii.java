class Solution {
    public double minAreaFreeRect(int[][] points) {
        // Create a map to store the pairs of points and their midpoints.
        Map<String, List<int[]>> map = new HashMap<>();
        
        // Iterate over all pairs of points.
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int[] p1 = points[i];
                int[] p2 = points[j];
                
                // Calculate the midpoint and the distance squared between the points.
                double midX = (p1[0] + p2[0]) / 2.0;
                double midY = (p1[1] + p2[1]) / 2.0;
                double distSq = Math.pow(p1[0] - p2[0], 2) + Math.pow(p1[1] - p2[1], 2);
                
                // Create a key for the map using the midpoint and distance squared.
                String key = midX + "," + midY + "," + distSq;
                
                // Add the pair of points to the map.
                map.putIfAbsent(key, new ArrayList<>());
                map.get(key).add(new int[]{i, j});
            }
        }
        
        double min = Double.MAX_VALUE; // Initialize the minimum area to a large value.
        
        // Iterate over the map to find rectangles.
        for (List<int[]> pairs : map.values()) {
            for (int i = 0; i < pairs.size(); i++) {
                for (int j = i + 1; j < pairs.size(); j++) {
                    int[] pair1 = pairs.get(i);
                    int[] pair2 = pairs.get(j);
                    
                    int[] p1 = points[pair1[0]];
                    int[] p2 = points[pair1[1]];
                    int[] p3 = points[pair2[0]];
                    int[] p4 = points[pair2[1]];
                    
                    // Calculate the area of the rectangle.
                    double area = Math.sqrt(Math.pow(p1[0] - p3[0], 2) + Math.pow(p1[1] - p3[1], 2)) *
                                  Math.sqrt(Math.pow(p1[0] - p4[0], 2) + Math.pow(p1[1] - p4[1], 2));
                    
                    min = Math.min(min, area);
                }
            }
        }
        
        // Return the minimum area found, or 0 if no rectangle was found.
        return min == Double.MAX_VALUE ? 0 : min;
    }
}