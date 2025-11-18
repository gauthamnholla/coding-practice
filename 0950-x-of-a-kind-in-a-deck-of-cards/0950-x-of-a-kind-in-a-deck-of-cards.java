class Solution {
    public boolean hasGroupsSizeX(int[] deck) {
        // HashMap<Integer,Integer> map  = new HashMap<>();
        // for(int x : deck){
        //     map.put(x,map.getOrDefault(x,0)+1);
        // }
        // int gcd = -1;
        // for(int val : map.values()){
        //     if(gcd==-1){
        //         gcd = val;
        //     }
        //     else{
        //         gcd = getgcd(gcd,val);
        //     }
        // }
        // return gcd>1;

        int[] temp = new int[deck.length+1];
        for(int x : deck){
            temp[x]++;
        }
        int gcd = -1;
        for(int val : temp){
            if(val>0){
                if(gcd==-1){
                    gcd = val;
                }
                else{
                    gcd = getgcd(gcd,val);
                }
            }
        }
        return gcd>1;
    }
    public static int getgcd(int a, int b){
        if(b==0){
            return a;
        }
        return getgcd(b,a%b);
    }
}