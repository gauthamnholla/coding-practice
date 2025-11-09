class Solution {
    public char kthCharacter(long k, int[] operations) {
        int shift = 0; // Total number of +1 (mod 26) shifts
        List<Long> lengths = new ArrayList<>();
        long len = 1;  // Initial string length ("a")

        // Step 1: Compute lengths after each operation
        for (int op : operations) {
            len *= 2;          // Each operation doubles the string
            lengths.add(len);
            if (len >= k) break; // Stop once total length reaches k
        }

        // Step 2: Work backwards to find where k lies
        for (int i = lengths.size() - 1; i >= 0; i--) {
            long half = lengths.get(i) / 2;
            int op = operations[i];

            // If k lies in the second half, adjust position and shift
            if (k > half) {
                k -= half;
                if (op == 1) shift++; // Add one shift for operation 1
            }
            // If in first half, nothing changes
        }

        // Step 3: Apply total shift to 'a' and wrap around alphabet
        return (char) ((('a' - 'a' + shift) % 26) + 'a');
    }
}
