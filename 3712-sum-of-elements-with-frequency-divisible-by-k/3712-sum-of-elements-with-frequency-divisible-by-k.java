class Solution {
    public int sumDivisibleByK(int[] nums, int k) {
        int[]freq = new int[101];
        int sum = 0;
        for(int num : nums){
            freq[num]++;
        }
        for(int i=0;i<101;i++){
            if(freq[i]%k == 0)sum+=freq[i]*i;
        }

        return sum;
    }
}