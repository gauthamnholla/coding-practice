import java.util.*;

class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int fuel = startFuel, stops = 0, i = 0, n = stations.length;

        while (fuel < target) {
            // Add all stations we can reach with current fuel
            while (i < n && stations[i][0] <= fuel) {
                pq.add(stations[i][1]);
                i++;
            }

            // No reachable stations left and we can't reach target
            if (pq.isEmpty()) return -1;

            // Refuel from the biggest fuel station passed
            fuel += pq.poll();
            stops++;
        }

        return stops;
    }
}
