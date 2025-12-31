class Solution {
    boolean isConnected(Map<Integer, Set<Integer>> map, int k, int i, int j){
    for(Integer t: map.get(i)) k -= (map.get(j).contains(t))?1:0;
    return (k <= 0);
}

int find(int node, int[] par){ System.out.println(node + " " + par[node]);
    return par[node] = ((par[node] == node)?node: find(par[node], par));
}

int add(int a, int b, int[] par){
    int pa = find(a, par), pb = find(b, par);
    par[pb] = pa;
    return (pa != pb)?1:0;    // return 1 if bothe are in different group before ese return 0
}

int numberOfComponents(int[][] p, int k) {
    int count = p.length;
    int[] par = new int[p.length];
    for(int i = 0; i < par.length; ++i) par[i] = i;
    Map<Integer, Set<Integer>> map = new HashMap<>();
    for(int i = 0; i < p.length; ++i){           // create the lookup map 
        for(int j = 0; j < p[i].length; ++j) map.computeIfAbsent(i, key -> new HashSet<>()).add(p[i][j]);
    }
    for(int i = 0; i < p.length; ++i){           
        for(int j = i+1; j < p.length; ++j){System.out.println(i + " " + j);
            if(isConnected(map, k, i, j)) count -= add(i, j, par);  // check connected or not, if connected then add them and if we are merging two disconnected group then reduce the total count by 1
        }
    }
    return count;
}
}