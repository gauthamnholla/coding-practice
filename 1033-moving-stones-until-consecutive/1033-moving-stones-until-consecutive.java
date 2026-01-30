class Solution {
    public int[] numMovesStones(int a, int b, int c) {
        int [] arr = new int[3];
        arr[0]=a;
        arr[1]=b;
        arr[2]=c;
        Arrays.sort(arr);
        int min=0;
        int max=0;
        if(arr[2]-arr[0]==2){
            return new int[]{0,0};
        }
        if(Math.min(arr[1]-arr[0],arr[2]-arr[1])<=2){
            min=1;
        }
        else{
            min=2;
        }
        max=arr[2]-arr[0]-2;
        return new int[]{min,max};
    }
}