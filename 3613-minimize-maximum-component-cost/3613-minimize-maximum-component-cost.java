class Solution {
    //DSU with rank
    int[] p;
    int[] rank;
    //to find parent[x]
    int find(int x){
        return p[x] = (x == p[x]?x:find(p[x]));
    }
    //combine two componenets based on rank
    void union(int x,int y){
        //find x parent
        int px = find(x);
        //find y parent
        int py = find(y);
        //if they are same that means they are single components
        //no need to combine
        if(px == py) return;
        //combine using rank
        if(rank[px]>rank[py])
            p[py] = px;
        else if(rank[py]>rank[px])
            p[px] = py;
        else{
            p[px] = py;
            rank[py]++;
        } 
    }
    //check if they can be formed atmost k compoennets with weight <=mid
    boolean check(int n ,int [][] edges,int k,int mid){
        //each node is its own parent at start
        for(int i=0;i<n;i++)
            p[i] = i;
        //rank of each node is 0
        Arrays.fill(rank,0);
        //combine the edges 
        for(int[] e:edges){
            if(e[2]<=mid) 
                union(e[0],e[1]);
            else break;
        }
        //count the components
        int components = 0;
        for(int i = 0;i<n;i++){
            if(p[i] == i)
                components++;
        }
        return components<=k;
    }
    public int minCost(int n, int[][] edges, int k) {
        p= new int[n];
        rank = new int[n];
        int st = 0,end = 1000000;
        int ans = 0;
        //you can skip this sorting
        Arrays.sort(edges,(a,b)->a[2]-b[2]);

        //binary search on weights (0,10^6)
        while(st<=end){
            int mid = (st+end)/2;
            if(check(n,edges,k,mid)){
                ans = mid;
                end = mid-1;
            }
            else st = mid+1;
        }
        return ans;
    }
}