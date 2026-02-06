class Solution {
    public int minElement(int[] nums) {
        int arr[] = new int[nums.length];
        for(int i = 0 ; i < nums.length; i++) {
            int sum = 0;
            while(nums[i] > 0){
                int temp = nums[i] % 10;
                sum += temp;
                nums[i] = nums[i]/10;
            }
            arr[i] = sum;
        }
        int min = minEle(arr);

        return min;
    }
    public int minEle(int[] arr){
        int min = arr[0];
        for(int n:arr){
            if(n < min){
                min = n;
            }
        }

        return min;
    }
}