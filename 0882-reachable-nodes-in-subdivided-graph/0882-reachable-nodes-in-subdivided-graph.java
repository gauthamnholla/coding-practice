class Solution {
    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        List<List<int[]>> adj = new ArrayList<>(n);
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }
        for(int[] edge : edges){
            adj.get(edge[0]).add(new int[]{edge[1], edge[2]});
            adj.get(edge[1]).add(new int[]{edge[0], edge[2]});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(b[1], a[1]));
        pq.offer(new int[]{0, maxMoves});
        Map<Integer, Integer> visited = new HashMap<>();
        int nodesReached = 0;

        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int u = curr[0], movesLeft = curr[1];
            if(visited.containsKey(u)) continue;
            
            visited.put(u, movesLeft);
            nodesReached++;

            for(int[] pair : adj.get(u)){
                int v = pair[0], cost = pair[1];
                if(!visited.containsKey(v) && movesLeft > cost){
                    pq.offer(new int[]{v, movesLeft - cost - 1});
                }
            }
        }

        for(int[] edge : edges){
            int a = visited.getOrDefault(edge[0], 0);
            int b = visited.getOrDefault(edge[1], 0);
            nodesReached += Math.min(edge[2], a + b);
        }
        return nodesReached;
    }
}