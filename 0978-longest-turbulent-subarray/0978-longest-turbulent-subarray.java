class Solution {
    public int maxTurbulenceSize(int[] arr) {
        if(arr.length==1){
            return 1;
        }
        int max=0;
        int i=0;
        int j=1;
        boolean flag=false;
        boolean check=false;
        while(j<arr.length){
            if(arr[j-1]<arr[j] && i+1==j){
                flag=true;
                j++;
            }else if(arr[j-1]>arr[j] && i+1==j){
                flag=false;
                j++;
            }else if(arr[j]>arr[j-1] && flag==false){
                flag=true;
                j++;
            }else if(arr[j]<arr[j-1] && flag==true){
                flag=false;
                j++;
            }else if(arr[j]==arr[j-1]){
                check=true;
                max=Math.max(max,j-i);
                i=j;
                j++;
            }
            else{
                check=true;
                max=Math.max(max,j-i);
                i=j-1;
                // j++;
            }
        }
        
            max=Math.max(max,j-i);
        
        
        return max;
    }
}