class Solution {
    public int getXORSum(int[] arr1, int[] arr2) {
        int[] a = new int[31],b = new int[31];
        for(int n:arr1){
            for(int i=0;i<31;i++){
                if((n&(1<<i))!=0)a[i]++;
            }
        }
        for(int n:arr2){
            for(int i=0;i<31;i++){
                if((n&(1<<i))!=0)b[i]++;
            }
        }
        int ans=0;

        for(int i=0;i<31;i++){
            long res = (long)a[i]*b[i];
            if(res%2!=0)ans|=(1<<i);
        }
        return ans;
    }
}