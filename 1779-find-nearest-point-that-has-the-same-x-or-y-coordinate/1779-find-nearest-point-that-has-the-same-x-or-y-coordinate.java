class Solution {
    public int nearestValidPoint(int x, int y, int[][] points) {
        int n = points.length, min = Integer.MAX_VALUE, index = -1;

        for (int i = 0; i < n; i++) {
            if (points[i][0] == x || points[i][1] == y) {
                int temp = Math.abs(points[i][0] - x) + Math.abs(points[i][1] - y);
                if (temp < min) {
                    min = temp;
                    index = i;
                }
            }
        }

        return index;
    }
}