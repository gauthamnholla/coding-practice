class Solution {
    public int longestMountain(int[] arr) {
        if(arr.length < 3){
            return 0;
        }
        int max = 0;
        for(int i=1;i<arr.length-1;i++){
            int num = arr[i];
            if((num > arr[i-1]) && (num > arr[i+1])){
                int left = i-1;
                int right = i+1;
                while (left > 0 && arr[left - 1] < arr[left]) {
                    left--;
                }
                while (right < arr.length - 1 && arr[right + 1] < arr[right]) {
                    right++;
                }
                max = Math.max(max,right-left+1);
            }
        }
        return max;
    }
}