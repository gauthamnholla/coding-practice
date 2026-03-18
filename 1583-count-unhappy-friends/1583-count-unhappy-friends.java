class Solution {
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        //store rank of friends in hashmap 
        HashMap<Integer,HashMap<Integer,Integer>> hm = new HashMap<>();
        for(int i=0;i<n;i++){
            hm.put(i,new HashMap<>());
            for(int j=0;j<preferences[i].length;j++){
                hm.get(i).put(preferences[i][j],j);
            }
        }
        HashMap<Integer,Integer> partner = new HashMap<>();
        for(int i[] :pairs){
            partner.put(i[0],i[1]);
            partner.put(i[1],i[0]);
        }
        int count=0;
        for(int x=0;x<n;x++){//iterate all the persons
            int pair = partner.get(x);//get the pair of present person
            for(int u:preferences[x]){//iterate  preferences of current person
                if(u==pair)break;//if found the pair at starting break since he is happy
                int v= partner.get(u);//else get the current partner of current person
                if(hm.get(u).get(x)<hm.get(u).get(v)){//if pref prefer current person over his partner 
                    count++;
                    break;
                }
            }
        }
        return count;
    }
}