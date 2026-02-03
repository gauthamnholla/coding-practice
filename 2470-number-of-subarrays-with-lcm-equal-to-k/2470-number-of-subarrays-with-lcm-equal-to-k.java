class Solution {
    public int lcm(int a,int b){
        if (a == 0 || b == 0) return 0;
        int l=a/gcd(a,b)*b;
        return l;
    }
    public int gcd(int a,int b){
        if(b==0) return a;
        return gcd(b,a%b);
    }

    public int subarrayLCM(int[] nums, int k) {
        int n=nums.length;
        int ans=0;
        for(int i=0;i<n;i++){
            int resl=1;
            for(int j=i;j<n;j++){
                if (k % nums[j] != 0) break;
                resl=lcm(resl,nums[j]);
                if(resl>k) break;
                else if(resl==k) ans++;
            }
        }
        return ans;
    }
}