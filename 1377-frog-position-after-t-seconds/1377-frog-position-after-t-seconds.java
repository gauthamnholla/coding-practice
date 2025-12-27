class Solution {
    ArrayList<Integer>[] adj;
    Set<Integer> path;
    public double frogPosition(int n, int[][] edges, int t, int target) {
        path = new HashSet<>();
        adj = new ArrayList[n+1];
        for(int i = 0;i <= n;i++) adj[i] = new ArrayList<>();
        for(int[] edge: edges){
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        boolean[] visit = new boolean[n+1];
        frogMoveCal(1, t, target, visit, new HashSet<>());
        if(path.isEmpty()) return 0;
        else{
            double ans = 1;
            for(int ele: path){
                int sz = adj[ele].size();
                if(sz == 0 || ele == target) continue;
                if(ele == 1) ans *= ((double) 1 /sz);
                else ans *= ((double) 1 / (sz-1));
            }
            return ans;
        }
    }
    public void frogMoveCal(int node, int time, int target, boolean[] visit, Set<Integer> curPath){
        if(time == 0){
            if(node == target){
                curPath.add(node);
                path = new HashSet<>(curPath);
                curPath.remove(node);
            }
            return;
        }
        int sz = adj[node].size();
        if(node == target){
            if((sz == 1 && node != 1) || sz == 0){
                curPath.add(node);
                path = new HashSet<>(curPath);
                curPath.remove(node);
            }
            return;
        }
        visit[node] = true;
        curPath.add(node);
        for(int nxt: adj[node]){
            if(!visit[nxt]){
                frogMoveCal(nxt, time-1, target, visit, curPath);
            }
        }
        curPath.remove(node);
    }
}