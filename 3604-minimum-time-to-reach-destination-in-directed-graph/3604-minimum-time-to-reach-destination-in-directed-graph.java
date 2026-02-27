class Solution {
    public class Pair {
        int vtx;
        int cost;

        public Pair(int vtx, int cost) {
            this.vtx = vtx;
            this.cost = cost;
        }
    }

    public int minTime(int n, int[][] edges) {
        HashMap<Integer, HashMap<Integer, List<int[]>>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new HashMap<>());
        }
        for (int[] arr : edges) {
            int a = arr[0];
            int b = arr[1];
            int st = arr[2];
            int end = arr[3];
            map.get(a).putIfAbsent(b, new ArrayList<>());
            map.get(a).get(b).add(new int[] { st, end });
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);

        int[] des = new int[n];
        Arrays.fill(des, (int) (1e9));
        pq.add(new Pair(0, 0));
        des[0] = 0;
        while (!pq.isEmpty()) {
            Pair rv = pq.poll();
            int node = rv.vtx;
            int time = rv.cost;
            if (des[node] < time) {
                continue;
            }
            for (int nbrs : map.get(node).keySet()) {
                for (int[] arr : map.get(node).get(nbrs)) {
                    int st = arr[0];
                    int end = arr[1];
                    int tm = Math.max(time, st);
                    if (des[nbrs] > tm+1 && tm <= end) {
                        des[nbrs] = tm+1;
                        pq.add(new Pair(nbrs, tm+1));
                    }
                }
            }
        }
        if (des[n - 1] == (int) (1e9)) {
            return -1;
        }
        return des[n - 1];
    }
}