import java.util.*;

class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) return 0;

        int m = routes.length;

        // Map from stop -> list of bus indices serving that stop
        Map<Integer, List<Integer>> stopToBuses = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int stop : routes[i]) {
                stopToBuses.computeIfAbsent(stop, k -> new ArrayList<>()).add(i);
            }
        }

        // If there is no bus from source or no bus to target, quick check (optional)
        if (!stopToBuses.containsKey(source) || !stopToBuses.containsKey(target)) {
            // If target isn't served by any bus, impossible
            if (!stopToBuses.containsKey(target)) return -1;
            // If no bus serves source, impossible
            if (!stopToBuses.containsKey(source)) return -1;
        }

        boolean[] visitedBus = new boolean[m];
        Queue<int[]> q = new ArrayDeque<>(); // pair: [busIndex, busesTaken]

        // enqueue all buses that serve the source
        for (int bus : stopToBuses.getOrDefault(source, Collections.emptyList())) {
            visitedBus[bus] = true;
            q.add(new int[] { bus, 1 });
        }

        // BFS
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int bus = cur[0];
            int busesTaken = cur[1];

            // iterate all stops served by this bus
            for (int stop : routes[bus]) {
                if (stop == target) return busesTaken; // reached target using this bus

                // for each bus serving this stop, try to board it
                List<Integer> busesAtStop = stopToBuses.get(stop);
                if (busesAtStop == null) continue;

                for (int nb : busesAtStop) {
                    if (!visitedBus[nb]) {
                        visitedBus[nb] = true;
                        q.add(new int[] { nb, busesTaken + 1 });
                    }
                }
                // Clear list to prevent reprocessing this stop many times
                stopToBuses.put(stop, new ArrayList<>());
            }
        }

        return -1;
    }
}
