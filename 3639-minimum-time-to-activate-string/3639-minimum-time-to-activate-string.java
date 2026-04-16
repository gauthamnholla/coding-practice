class Solution {
        public int minTime(String s, int[] order, int k) {
        int n = s.length();
        // Use a TreeSet to maintain a sorted list of indices
        TreeSet<Integer> pos = new TreeSet<>();
        pos.add(-1);
        pos.add(n);

        // Iterate through the order of removal
        for (int t = 0; t < order.length; ++t) {
            int i = order[t];

            // Find the elements in the sorted set that bracket the current index 'i'
            Integer r = pos.ceiling(i); // 'r' is the smallest element >= i
            Integer l = pos.floor(i);  // 'l' is the largest element <= i

            // The 'cost' to remove an item is the product of the distances to its neighbors
            k -= (long) (i - l) * (r - i);
            pos.add(i);

            // If the total cost is exhausted, return the current time 't'
            if (k <= 0) {
                return t;
            }
        }

        // If all items are removed and k is not exhausted, return -1
        return -1;
    }
}