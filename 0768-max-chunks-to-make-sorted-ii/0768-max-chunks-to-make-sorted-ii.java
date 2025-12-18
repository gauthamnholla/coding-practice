class Solution {
    public int maxChunksToSorted(int[] arr) {
        int n=arr.length;
        int[] maxofLeft=new int[n];
        int[] minOfRigth=new int[n];
        maxofLeft[0]=arr[0];
        for(int i=1;i<n;i++){
            maxofLeft[i]=Math.max(maxofLeft[i-1],arr[i]);
        }
        minOfRigth[n-1]=arr[n-1];
        for(int i=n-2;i>=0;i--){
            minOfRigth[i]=Math.min(minOfRigth[i+1],arr[i]);
        }
        int res=0;
        for(int i=0;i<n-1;i++){
            if(maxofLeft[i]<=minOfRigth[i+1]) res++;
        }
        return res+1;

    }
}