class Router {
    // Max number of packets the router can hold in memory at once
    private final int size;

    // destination -> list of timestamps (sorted by arrival)
    private final Map<Integer, List<Integer>> counts;

    // Encoded packet key -> actual packet [source, destination, timestamp]
    private final Map<Long, int[]> packets;

    // FIFO queue to track insertion order (for forwarding oldest packet first)
    private final Queue<Long> queue;

    // Constructor: Initialize router with a fixed memory capacity
    public Router(final int memoryLimit) {
        this.size = memoryLimit;
        this.packets = new HashMap<>();
        this.counts = new HashMap<>();
        this.queue = new LinkedList<>();
    }

    // Add a packet into router memory
    public boolean addPacket(final int source, final int destination, final int timestamp) {
        // Unique identifier for packet = source + destination + timestamp
        final long key = encode(source, destination, timestamp);

        // If packet already exists (duplicate), reject
        if(packets.containsKey(key))
            return false;

        // If buffer is full, forward (evict) oldest packet
        if(packets.size() >= size)
            forwardPacket();

        // Store packet in memory
        packets.put(key, new int[] { source, destination, timestamp });
        queue.offer(key);  // track FIFO order

        // Track timestamp for this destination
        counts.putIfAbsent(destination, new ArrayList<>());
        counts.get(destination).add(timestamp);

        return true; // successfully added
    }

    // Forward (remove) the oldest packet and return it
    public int[] forwardPacket() {
        if(packets.isEmpty())
            return new int[] {}; // no packet to forward

        // Remove oldest packet from queue
        final long key = queue.poll();
        final int[] packet = packets.remove(key);

        if(packet == null)
            return new int[]{};

        // Update counts: remove the earliest timestamp for that destination
        final int destination = packet[1];
        final List<Integer> list = counts.get(destination);
        list.remove(0);  // assumes list is insertion-ordered

        return packet;  // return the forwarded packet
    }

    // Query how many packets went to a destination within [startTime, endTime]
    public int getCount(final int destination, final int startTime, final int endTime) {
        final List<Integer> list = counts.get(destination);
        if(list == null || list.isEmpty())
            return 0;

        // Binary search for first timestamp >= startTime
        final int left = lowerBound(list, startTime);

        // Binary search for first timestamp > endTime
        final int right = upperBound(list, endTime);

        return right - left; // number of timestamps in the range
    }

    // Encode source, destination, timestamp into a single unique key (64-bit long)
    private long encode(final int source, final int destination, final int timestamp) {
        return ((long)source << 40) | ((long)destination << 20) | timestamp;
    }

    // Binary search: find first index where value >= target
    private int lowerBound(final List<Integer> list, final int target) {
        int low = 0, high = list.size();

        while(low < high) {
            final int mid = (low + high) >>> 1;
            if(list.get(mid) < target) low = mid + 1;
            else high = mid;
        }
        return low;
    }

    // Binary search: find first index where value > target
    private int upperBound(final List<Integer> list, final int target) {
        int low = 0, high = list.size();

        while(low < high) {
            final int mid = (low + high) >>> 1;

            if(list.get(mid) <= target)
                low = mid + 1;
            else
                high = mid;
        }
        return low;
    }
}
