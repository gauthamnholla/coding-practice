class Solution {
    int mod = 1000000007;
    public int countPairs(int[] deliciousness) {
        HashMap<Integer,Integer> map = new HashMap<>();
        long ans = 0;
        for(int el : deliciousness){
            int power = 1;
            for(int i = 0; i<=21; i++){
                int y = power - el;
                if(map.containsKey(y)){
                    ans += (map.get(y)) % mod;
                }
                power <<= 1;
            }
            map.put(el,map.getOrDefault(el,0)+1);
        }
        return (int)(ans%mod);
    }
}