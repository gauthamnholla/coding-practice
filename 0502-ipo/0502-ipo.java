import java.util.*;

class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        // Pair projects as (requiredCapital, profit)
        int[][] projects = new int[n][2];
        for (int i = 0; i < n; i++) {
            projects[i][0] = capital[i];
            projects[i][1] = profits[i];
        }
        // Sort by required capital ascending
        Arrays.sort(projects, (a, b) -> Integer.compare(a[0], b[0]));

        // Max-heap for profits of projects we can afford
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        long curr = w;
        int idx = 0;

        // Do up to k projects
        for (int i = 0; i < k; i++) {
            // Add all projects affordable with current capital
            while (idx < n && projects[idx][0] <= curr) {
                maxHeap.offer(projects[idx][1]);
                idx++;
            }
            // If no available project, break early
            if (maxHeap.isEmpty()) break;

            // Pick the most profitable available project
            curr += maxHeap.poll();
        }

        return (int) curr;
    }
}
