class Solution {
    public long maxPoints(int[] technique1, int[] technique2, int k) {
        long sum=0;
        int n=technique1.length;
        int res[][]=new int[n][2];
        for(int i=0;i<n;i++){
            res[i][0]=technique1[i];
            res[i][1]=technique2[i];
        }
        Arrays.sort(res,(a,b)->(b[0]-b[1])-(a[0]-a[1]));
        for(int i=0;i<k;i++){
            sum+=res[i][0];
        }
        for(int i=k;i<n;i++){
            sum+=Math.max(res[i][0],res[i][1]);
        }
        return sum;
    }
}