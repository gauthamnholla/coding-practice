class TaskManager {
    // Priority queue to store tasks as [priority, taskId], 
    // with highest priority first (and larger taskId as tie-breaker).
    private PriorityQueue<int[]> pq;

    // Maps to store the latest priority of each task and who owns it.
    private Map<Integer,Integer> taskPriority;
    private Map<Integer,Integer> taskOwner;

    // Constructor: initializes data structures and adds initial tasks
    public TaskManager(List<List<Integer>> tasks) {
        // Custom comparator for priority queue:
        //   - Higher priority comes first
        //   - If priority is the same, larger taskId comes first
        pq = new PriorityQueue<>((a,b) -> {
            if (b[0] != a[0]) return b[0] - a[0];
            return b[1] - a[1];
        });

        taskPriority = new HashMap<>();
        taskOwner = new HashMap<>();

        // Add each task from the input list
        for (List<Integer> t : tasks) 
            add(t.get(0), t.get(1), t.get(2));
    }

    // Add a new task with (userId, taskId, priority)
    public void add(int userId, int taskId, int priority) {
        pq.add(new int[]{priority, taskId}); // push into heap
        taskPriority.put(taskId, priority);  // track latest priority
        taskOwner.put(taskId, userId);       // track owner
    }

    // Edit priority of an existing task
    public void edit(int taskId, int newPriority) {
        pq.add(new int[]{newPriority, taskId}); // push updated task
        taskPriority.put(taskId, newPriority);  // update latest priority
    }

    // Mark a task as removed (lazy removal by setting priority = -1)
    public void rmv(int taskId) {
        taskPriority.put(taskId, -1);
    }

    // Execute (remove) the top valid task and return its owner
    public int execTop() {
        while (!pq.isEmpty()) {
            int[] t = pq.poll();  // get top task
            int p = t[0], id = t[1];

            // Check if this task's priority matches the latest recorded one
            if (taskPriority.getOrDefault(id, -2) == p) {
                taskPriority.put(id, -1); // mark task as executed
                return taskOwner.getOrDefault(id, -1); // return owner
            }
            // If not matching, it's outdated → skip
        }
        return -1; // No valid tasks left
    }
}
