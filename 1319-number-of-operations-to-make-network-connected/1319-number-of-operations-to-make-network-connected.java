class Solution {
    int extraWire;
    public int makeConnected(int n, int[][] connections) {
        extraWire = 0;
        ArrayList<Integer>[] adj = new ArrayList[n];
        for(int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for(int[] conn : connections) {
            adj[conn[0]].add(conn[1]);
            adj[conn[1]].add(conn[0]);
        }

        int comp = 0;
        boolean[] visit = new boolean[n];
        for(int i = 0;i < n;i++){
            if(!visit[i]){
                comp++;
                dfs(adj, i, -1, visit);
            }
        }

        if(comp-1 > extraWire/2) return -1;
        return comp-1;
    }
    public void dfs(ArrayList<Integer>[] adj, int node, int pre, boolean[] visit){
        visit[node] = true;
        for(int nxt: adj[node]){
            if(!visit[nxt]){
                dfs(adj, nxt, node, visit);
            }
            else if(nxt != pre) extraWire++;
        }
    }
}