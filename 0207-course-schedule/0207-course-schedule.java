class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[numCourses];

        // build graph
        for (int i = 0; i < numCourses; i++) graph.add(new ArrayList<>());
        for (int[] p : prerequisites) {
            int course = p[0], pre = p[1];
            graph.get(pre).add(course);
            indegree[course]++;
        }

        // start with all courses that have no prerequisites
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++)
            if (indegree[i] == 0) q.offer(i);

        int taken = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            taken++;
            for (int next : graph.get(cur)) {
                if (--indegree[next] == 0) q.offer(next);
            }
        }

        return taken == numCourses;
    }
}
