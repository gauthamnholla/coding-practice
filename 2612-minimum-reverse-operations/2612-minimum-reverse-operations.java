class Solution {
    public int[] minReverseOperations(int n, int p, int[] banned, int k) {
        int[] answer = new int[n];
        Arrays.fill(answer, -1);

        // Step 1: Mark banned positions
        Set<Integer> bannedSet = new HashSet<>();
        for (int b : banned) bannedSet.add(b);

        // Step 2: Track unvisited positions using two TreeSets
        // even indices in one set, odd in another (for efficient range queries)
        TreeSet<Integer> unvisited[] = new TreeSet[]{new TreeSet<>(), new TreeSet<>()};
        for (int i = 0; i < n; i++) {
            if (i != p && !bannedSet.contains(i)) {
                unvisited[i % 2].add(i);
            }
        }

        // Step 3: BFS
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(p);
        answer[p] = 0;
        int ops = 0;

        while (!queue.isEmpty()) {
            ops++;
            int size = queue.size();

            while (size-- > 0) {
                int i = queue.poll();

                // Range of reachable positions from i
                // new_pos = 2l + k - 1 - i, l in [max(0,i-k+1), min(n-k,i)]
                // min new_pos when l is min: 2*max(0,i-k+1) + k - 1 - i
                // max new_pos when l is max: 2*min(n-k,i) + k - 1 - i
                int lo = Math.max(0, i - k + 1);
                int hi = Math.min(n - k, i);
                int minPos = 2 * lo + k - 1 - i;
                int maxPos = 2 * hi + k - 1 - i;

                // All reachable positions have same parity as minPos (step of 2)
                // Use TreeSet to efficiently find unvisited positions in [minPos, maxPos]
                TreeSet<Integer> set = unvisited[minPos % 2];
                Integer cur = set.ceiling(minPos);

                while (cur != null && cur <= maxPos) {
                    answer[cur] = ops;
                    queue.offer(cur);
                    set.remove(cur);
                    cur = set.ceiling(minPos);
                }
            }
        }

        return answer;
    }
}
