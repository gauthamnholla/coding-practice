class Solution {
    public int numFriendRequests(int[] ages) {
        int res=0;
        Arrays.sort(ages);

        for(int i=ages.length-1;i>0;i--){
            for(int j=i-1;j>=0;j--){
                if(ages[j] <= (double)0.5*ages[i]+7) break;
                if(ages[i]==ages[j]) res+=2;
                else res++;
            }
        }

        return res;
    }
}