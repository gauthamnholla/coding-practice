class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        int freq[]=new int[1001];
        for(int tr[]:trips){
            freq[tr[1]]+=tr[0];
            freq[tr[2]]-=tr[0];
        }
        int sum=0;
        for(int i:freq){
            sum+=i;
            if(sum>capacity){
                return false;
            }
        }
        return true;
    }
}