class Solution {
    private int Encrypt(int num) {
        int temp = num, m = 0;
        while(temp > 0) {
            int d = temp % 10;
            if(d > m) m = d;
            temp /= 10;
        }
        int res = 0, p = 1;
        while(num > 0) {
            res += m * p;
            p *= 10;
            num /= 10;
        }
        return res;
    }
    public int sumOfEncryptedInt(int[] nums) {
        int ans = 0;
        for(int i = 0; i < nums.length; i++) {
            ans += Encrypt(nums[i]);
        }
        return ans;
    }
}