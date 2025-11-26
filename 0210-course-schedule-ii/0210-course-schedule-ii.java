class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        // Create graph (adjacency list)
        List<Integer>[] graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) graph[i] = new ArrayList<>();

        // indegree[i] = how many courses must be taken before i
        int[] indegree = new int[numCourses];

        // Build graph and indegree
        for (int[] p : prerequisites) {
            int course = p[0];
            int pre = p[1];
            graph[pre].add(course);
            indegree[course]++;
        }

        // Queue for courses that have no prerequisites
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++)
            if (indegree[i] == 0) q.offer(i);

        int[] order = new int[numCourses];
        int idx = 0;

        // Process courses with no remaining prerequisites
        while (!q.isEmpty()) {
            int cur = q.poll();
            order[idx++] = cur;

            // Reduce indegree of dependent courses
            for (int next : graph[cur]) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    q.offer(next); // now ready to take
                }
            }
        }

        // If we placed all courses, return the order
        if (idx == numCourses) return order;

        // Otherwise, cycle exists → impossible
        return new int[0];
    }
}
