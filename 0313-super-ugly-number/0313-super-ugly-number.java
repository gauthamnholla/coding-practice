class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        // Priority queue to get the smallest current number
        PriorityQueue<long[]> minPQ = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));

        // Initialize the priority queue with the first prime multiples
        for (int prime : primes) {
            minPQ.offer(new long[] {prime, 0, prime}); // [value, index in output, prime factor]
        }

        // Array to store the ugly numbers
        long[] output = new long[n];
        output[0] = 1; // The first super ugly number is always 1

        // Populate the output array
        for (int i = 1; i < n; i++) {
            long[] current = minPQ.poll();
            long value = current[0];
            int idx = (int)current[1];
            long prime = current[2];

            // Avoid adding duplicate values to the output array
            if (value != output[i - 1]) {
                output[i] = value;
            } else {
                i--; // If duplicate, redo this index
            }

            // Generate the next multiple of the current prime
            minPQ.offer(new long[] {output[idx + 1] * prime, idx + 1, prime});
        }

        return (int)output[n - 1];
    }
}