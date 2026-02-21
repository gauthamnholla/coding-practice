class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int max = 0;
        int ans = 0;
        for(int no : nums){
            max = Math.max(no, max);
        }
        int min = 1;

        while(min <= max){
            int mid = min + (max - min)/2;
            int res = sum(nums, mid);

            if(res <= threshold){
                ans = mid;
                max = mid - 1;
            }else{
                min = mid + 1;
            }
        }
        return ans;
    }
    public int sum(int nums[], int d){
        int sum = 0;
        for(int no : nums){
            sum += (no + d - 1)/d;
        }
        return sum;
    }
}