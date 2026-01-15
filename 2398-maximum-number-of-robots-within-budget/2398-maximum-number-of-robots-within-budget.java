class Solution {
        public int maximumRobots(int[] times, int[] costs, long budget) {
        long sum = 0;
        int i = 0, n = times.length;
        Deque<Integer> d = new LinkedList<Integer>();
        for (int j = 0; j < n; ++j) {
            sum += costs[j];
            while (!d.isEmpty() && times[d.peekLast()] <= times[j])
                d.pollLast();
            d.addLast(j);
            if (times[d.getFirst()] + (j - i + 1) * sum > budget) {
                if (d.getFirst() == i)
                    d.pollFirst();
                sum -= costs[i++];
            }
        }
        return n - i;
    }
}