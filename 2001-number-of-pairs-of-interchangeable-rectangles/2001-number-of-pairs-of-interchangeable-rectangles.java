class Solution {
    public long interchangeableRectangles(int[][] rectangles) {
        long totalCount = 0;
        int n = rectangles.length;
        double arr[] = new double[n];

        for (int i = 0; i < n; i++) {
            arr[i] = ((rectangles[i][0] * 1.0) / rectangles[i][1]);
        }

        Arrays.sort(arr);

        int c = 1;
        for (int i = 0; i < arr.length - 1; i++) {
            if (isEqual(arr[i], arr[i + 1])) {
                c++;
            } else if (c > 1) {
                long totalPair = (long)c * (c - 1) / 2;
                totalCount += totalPair;
                c = 1;
            }
        }
        if (c > 1) {
            long totalPair = (long)c * (c - 1) / 2;
            totalCount += totalPair;
        }
        return totalCount;
    }

    private boolean isEqual(double a, double b) {
        final double EPSILON = 1e-9;
        return Math.abs(a - b) < EPSILON;
    }
}

