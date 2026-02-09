class Solution {
    public int getLeastFrequentDigit(int n) {
        HashMap<Integer,Integer> map = new HashMap<>();
        while(n > 0){
            int temp = n % 10;
            map.put(temp, map.getOrDefault(temp, 0) + 1);
            n = n / 10;
        }
        int res = -1;
        int max = Integer.MAX_VALUE;
        for(int m:map.keySet()){
            int freq = map.get(m);
            if (freq < max || (freq == max && (res == -1 || m < res))) {
                max = freq;
                res = m;
            }
        }

        return res;
    }
}