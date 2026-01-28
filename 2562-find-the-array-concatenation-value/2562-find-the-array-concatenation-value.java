class Solution {
    public long findTheArrayConcVal(int[] nums) {
        long sum = 0;
        int i = 0;
        int j = nums.length - 1;

        while(i < j) {
            long noOfZeroes = getNoOfZeroes(nums[j]);
            sum = sum + (nums[i] * noOfZeroes) + nums[j];
            i++;
            j--;
        }

        if(i == j) sum = sum + nums[i];
        return sum;
    }

    public long getNoOfZeroes(int num) {
        long result = 1;
        while(num > 0) {
            result = result * 10;
            num = num / 10;
        }

        return result;
    }
}