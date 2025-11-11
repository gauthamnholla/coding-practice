class Solution {
    public int scheduleCourse(int[][] C) {
        // Step 1: Sort courses by their end day
        Arrays.sort(C, (a, b) -> a[1] - b[1]);

        // Step 2: Max-heap to track durations of selected courses
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        int total = 0; // Total time spent so far

        // Step 3: Go through each course
        for (int[] course : C) {
            int dur = course[0], end = course[1];

            // If course fits within its deadline, take it
            if (dur + total <= end) {
                total += dur;
                pq.add(dur);
            }
            // Otherwise, replace the longest course if current one is shorter
            else if (!pq.isEmpty() && pq.peek() > dur) {
                total += dur - pq.poll(); // Adjust total time
                pq.add(dur);
            }
        }

        // Step 4: Number of courses successfully scheduled
        return pq.size();
    }
}
