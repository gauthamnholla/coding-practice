class EventManager {

    // eventId -> current priority
    private Map<Integer, Integer> mp;

    // max heap: {priority, -eventId}
    private PriorityQueue<int[]> pq;

    public EventManager(int[][] events) {
        mp = new HashMap<>();

        pq = new PriorityQueue<>((a, b) -> {
            if (b[0] != a[0]) return b[0] - a[0]; // max priority first
            return b[1] - a[1]; // max (-id) first
        });

        for (int[] e : events) {
            int id = e[0];
            int pr = e[1];
            mp.put(id, pr);
            pq.offer(new int[]{pr, -id});
        }
    }
    
    public void updatePriority(int eventId, int newPriority) {
        mp.put(eventId, newPriority);
        pq.offer(new int[]{newPriority, -eventId}); // push new entry
    }
    
    public int pollHighest() {
        while (!pq.isEmpty()) {
            int[] top = pq.peek();
            int pr = top[0];
            int negId = top[1];
            int id = -negId;

            // valid entry check
            if (mp.containsKey(id) && mp.get(id) == pr) {
                pq.poll();
                mp.remove(id); // mark as removed
                return id;
            }

            pq.poll(); // discard wrong entry
        }

        return -1;
    }
}