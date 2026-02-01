class Solution {
    int sqr(int a) {
        return a * a;
    }

    //find lower bound of left boundry
    int findLeftBoundryIndex(int [][] points, int x_center , int y_center , int r) {

        int lo = 0, hi = points.length;

        while (lo < hi) {

            int mi = lo + (hi - lo ) / 2;

            if (x_center - r <= points[mi][0])
                hi = mi;
            else
                lo = mi + 1;

        }
        return hi;

    }
    public int[] countPoints(int[][] points, int[][] queries) {
        Arrays.sort(points, (a, b) -> a[0] - b[0]);

        int n = queries.length;
        int ans[] = new int[n];

        for (int i = 0; i < n; i++) {
            int x_center = queries[i][0], y_center = queries[i][1], r = queries[i][2];
            int index = findLeftBoundryIndex(points, x_center , y_center, r);
            int count = 0;

            for (int j = index; j < points.length && points[j][0] <= x_center + r; j++) {

                int x = points[j][0];
                int y = points[j][1];
                // square of distance from center
                int dist = sqr(x_center - x) + sqr(y_center - y);
                if (dist <= r * r)
                    count++;
            }
            ans[i] = count;
        }
        return  ans;
    }
}