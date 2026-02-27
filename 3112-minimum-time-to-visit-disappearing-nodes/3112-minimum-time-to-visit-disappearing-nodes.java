class Solution {
    public int[] minimumTime(int n, int[][] edges, int[] disappear) {
        List<List<int[]>> adj = new ArrayList<>();
        for(int i=0 ; i<n ; i++){
            adj.add(new ArrayList<>());
        }

        for(int[] edge : edges){
            adj.get(edge[0]).add(new int[]{edge[1],edge[2]});
            adj.get(edge[1]).add(new int[]{edge[0],edge[2]});
        }
        int[] ans = new int[n];
        Arrays.fill(ans,Integer.MAX_VALUE);
        ans[0]=0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);
        pq.add(new int[]{0,0});
        boolean[] vis = new boolean[n];
        while(!pq.isEmpty()){
            int[] x = pq.poll();

            if(vis[x[0]]) continue;
            vis[x[0]] = true;
            
            for(int[] nbr : adj.get(x[0])){
                int distance = x[1]+nbr[1];
                if(distance<disappear[nbr[0]] && distance<=ans[nbr[0]]){
                    ans[nbr[0]] = distance;
                    pq.add(new int[]{nbr[0],distance});
                }
            }
        }

        for(int i=0 ; i<n ; i++){
            if(ans[i]==Integer.MAX_VALUE){
                ans[i] = -1;
            }
        }
        return ans;
    }
}