class Solution {
    public long gcdSum(int[] nums) {
        int max = 0;
        int[] mxi = new int[nums.length];
        int[] prefixGcd = new int[nums.length];
        long ans = 0;

        for(int i = 0 ;i< nums.length ;i++){
            if(nums[i] > max) max = nums[i];

            mxi[i] = max;
            prefixGcd[i] = gcd(nums[i],mxi[i]);
        }

        Arrays.sort(prefixGcd);

        int i = 0;
        int j = prefixGcd.length-1;

        while(i<j){
            ans += gcd(prefixGcd[i],prefixGcd[j]);
            i++;
            j--;
        }

        return ans;
    }

    public int gcd(int a , int b){
        if(b == 0) return a;
        return gcd(b,a%b);
    }
}