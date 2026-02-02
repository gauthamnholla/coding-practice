class Solution {
    public long[] kthPalindrome(int[] queries, int intLength) {
        long max =0;
        for(int i=0;i<intLength;i++){
            max = max*10+9;
        }
        long[] ans=new long[queries.length];
        for(int i=0;i<queries.length;i++){
            int q = queries[i];
            
            int k = (intLength+1)/2;
            long delta = (long)Math.pow(10,k-1);
            long n = delta+q-1;
            long rev = n+0;
            if(intLength%2!=0){
                rev/=10;
            }
            while(rev>0){
                n = n*10+rev%10;
                rev/=10;
            }
            if(max<n || n<0)n=-1;
            ans[i] = n;
        }
        return ans;
    }
}