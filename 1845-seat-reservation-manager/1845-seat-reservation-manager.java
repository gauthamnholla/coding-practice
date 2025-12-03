class SeatManager {

    private int next;
    private PriorityQueue<Integer> heap;

    public SeatManager(int n) {
        next = 1;
        heap = new PriorityQueue<>();
    }

    public int reserve() {
        if (!heap.isEmpty() && heap.peek() < next) {
            return heap.poll();
        }

        next++;
        return next - 1;
    }

    public void unreserve(int seatNumber) {
        heap.offer(seatNumber);
    }
}

/**
 * Your SeatManager object will be instantiated and called as such:
 * SeatManager obj = new SeatManager(n);
 * int param_1 = obj.reserve();
 * obj.unreserve(seatNumber);
 */