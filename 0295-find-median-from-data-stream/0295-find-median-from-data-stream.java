import java.util.*;

class MedianFinder {
    private PriorityQueue<Integer> lowerHalf; // Max-heap for smaller numbers
    private PriorityQueue<Integer> upperHalf; // Min-heap for larger numbers

    public MedianFinder() {
        lowerHalf = new PriorityQueue<>(Collections.reverseOrder());
        upperHalf = new PriorityQueue<>();
    }

    // Add a new number
    public void addNum(int num) {
        // Step 1: Add number to the correct heap
        if (lowerHalf.isEmpty() || num <= lowerHalf.peek()) {
            lowerHalf.offer(num);
        } else {
            upperHalf.offer(num);
        }

        // Step 2: Balance the heaps so size difference ≤ 1
        if (lowerHalf.size() > upperHalf.size() + 1) {
            upperHalf.offer(lowerHalf.poll());
        } else if (upperHalf.size() > lowerHalf.size()) {
            lowerHalf.offer(upperHalf.poll());
        }
    }

    // Find the median of all numbers added
    public double findMedian() {
        if (lowerHalf.size() > upperHalf.size()) {
            return lowerHalf.peek(); // Odd count → top of max-heap
        }
        // Even count → average of tops
        return (lowerHalf.peek() + upperHalf.peek()) / 2.0;
    }
}
