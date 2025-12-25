class Solution {

    static int parent[];
    static int size[];

    int find(int x){
        if(parent[x]==x)return x;
        else return parent[x]=find(parent[x]);//backtracking
    }

    void union_by_size(int a, int b,int ans[]){//this function connects the group leaders of the two components/nodes a and b
        int gla=find(a);//gla is the group leader of a and find function helps to find that group leader
        int glb=find(b);//glb is the group leader of b and find function helps to find that group leader

        if(gla==glb){//if both the leaders are same congrats u have detected the cycle 
            ans[0]=a;
            ans[1]=b;
            return;

        }
        // now we will really connect the two components .......now how to connect the components?....jiska size bada hai usse chota size waale ko connect kardo
        if(size[gla]>size[glb]){
            parent[glb]=gla;
            size[gla]+=size[glb];
        }
        else{
            parent[gla]=glb;
            size[glb]+=size[gla];
        }
    }

    public int[] findRedundantConnection(int[][] edges) {
        parent=new int[edges.length+1];
        size=new int[edges.length+1];
        for(int i=1;i<edges.length+1;i++){
            parent[i]=i;
        }
        for(int i=1;i<edges.length+1;i++)size[i]=1;
        parent[0]=1101;//just for fun
        size[0]=1011;//just for fun
        int ans[]=new int[2];//ans array to store the ans

        for(int i=0;i<edges.length;i++){
            if(ans[0]!=0)break;//the last edge of the edge list in the cycle is also the first edge to get detected therefore once we detected the cycle end the loop
            union_by_size(edges[i][0],edges[i][1],ans);
        }

        return ans;
    }
}