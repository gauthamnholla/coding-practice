class Solution {
    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
        
        int n = passingFees.length;
        int[][] dist = new int[n][maxTime + 1];

        for(int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        } 

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if(a[1] != b[1]) {
                return a[1] - b[1];
            }
            return a[2] - b[2];
        });

        pq.add(new int[]{0, passingFees[0], 0});

        List<List<int[]>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for(int i = 0; i < edges.length; i++) {
            adj.get(edges[i][0]).add(new int[]{edges[i][1], edges[i][2]});
            adj.get(edges[i][1]).add(new int[]{edges[i][0], edges[i][2]});
        }

        while(!pq.isEmpty()) {

            int[] cur = pq.poll();

            int node = cur[0];
            int cost = cur[1];
            int time = cur[2];

            if(cost > dist[node][time]) {
                continue;
            }

            List<int[]> nxt = adj.get(node);

            for(int i = 0; i < nxt.size(); i++) {

                int[] nd = nxt.get(i);

                if((time + nd[1] <= maxTime) && (cost + passingFees[nd[0]] < dist[nd[0]][time + nd[1]])) {
                    pq.add(new int[]{nd[0], cost + passingFees[nd[0]], time + nd[1]});
                    dist[nd[0]][time + nd[1]] = cost + passingFees[nd[0]];
                } 
            }
        }

        int minn = Integer.MAX_VALUE;

        for(int i = 0 ; i <= maxTime ; i++) {
            minn = Math.min(minn, dist[n - 1][i]);
        }

        if(minn == Integer.MAX_VALUE) {
            return -1;
        }

        return minn;
    }
}