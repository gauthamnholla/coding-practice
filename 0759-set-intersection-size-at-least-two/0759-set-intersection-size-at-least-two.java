import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        // Get the number of intervals.
        int n = intervals.length;

        // Sort the intervals. The primary sorting key is the end point (ascending).
        // If end points are equal, sort by start point (ascending).
        // This greedy strategy prioritizes intervals that finish earlier.
        // O(n log n) time complexity for sorting.
        Arrays.sort(intervals, (a, b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0]; // Sort by start point if end points are the same
            }
            return a[1] - b[1]; // Sort by end point
        });

        // 'res' will store the selected points that form the intersection set S.
        List<Integer> res = new ArrayList<>();

        // For the first interval (after sorting), we must pick two points.
        // The optimal greedy choice is its end point and one less than its end point.
        res.add(intervals[0][1] - 1); // Add one point just before the end of the first interval
        res.add(intervals[0][1]);     // Add the end point of the first interval

        // Iterate through the rest of the sorted intervals.
        // O(n) time complexity for this loop.
        for (int i = 1; i < n; i++) {
            int start = intervals[i][0]; // Start of the current interval
            int end = intervals[i][1];   // End of the current interval
            int size = res.size();       // Current number of points selected in S

            // Get the two largest points currently in our set S.
            // These are the most recently added points and are most likely to satisfy
            // the current interval due to the sorting strategy.
            int last = res.get(size - 1);         // The largest point in S
            int secondLast = res.get(size - 2);   // The second largest point in S

            // Check how the current interval [start, end] relates to the points in S.
            if (start > last) {
                // Case 1: The current interval starts after the largest point in S.
                // This means none of the points currently in S can cover this interval.
                // We must add two new points from this interval.
                // Greedily pick 'end - 1' and 'end' for this interval.
                res.add(end - 1);
                res.add(end);
            } else if (start == last) {
                // Case 2: The current interval starts exactly at the largest point in S.
                // This means 'last' is one point covering this interval.
                // We need one more point. Greedily add 'end' of the current interval.
                // This ensures 'last' (which is 'start') and 'end' cover this interval.
                res.add(end);
            } else if (start > secondLast) {
                // Case 3: The current interval starts after the second largest point in S,
                // but at or before the largest point in S (i.e., secondLast < start <= last).
                // This means 'last' is one point covering this interval.
                // 'secondLast' does not cover this interval.
                // We need one more point. Greedily add 'end' of the current interval.
                res.add(end);
            }
            // Case 4: (Implicit) If start <= secondLast.
            // This means both 'secondLast' and 'last' are >= start.
            // Since points in 'res' are added in increasing order (or at least non-decreasing for the last two),
            // and 'secondLast' < 'last' (as they are distinct points from an interval end and end-1, or distinct ends),
            // if start <= secondLast, then both 'secondLast' and 'last' are within the current interval [start, end].
            // So, the interval is already covered by at least two points from S. No new points needed.
        }

        // The size of the list 'res' is the minimum size of the set S.
        return res.size();
    }
}