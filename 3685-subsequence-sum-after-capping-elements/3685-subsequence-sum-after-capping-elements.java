class Solution {
    int getUpperBound(int val,int nums[]){
        int n = nums.length;
        int low=0;
        int high=n-1;
        int ans=n;
        while(low<=high){
            int mid=(low+high)/2;
            if(nums[mid]>val) {
                ans=mid;
                high=mid-1;
            }
            else low=mid+1;
        }
        return ans;
    }
    public boolean[] subsequenceSumAfterCapping(int[] nums, int k) {
        int n = nums.length;
        boolean ans[] = new boolean[n];
        Arrays.sort(nums);
        boolean dp[][] = new boolean[n][k+1];
        dp[0][0]=true;
        if(nums[0]<=k) dp[0][nums[0]]=true;
        for(int i=1;i<n;i++){
            int ele = nums[i];
            boolean temp[] = new boolean[k+1];
            for(int j=0;j<=k;j++){
                if(dp[i-1][j] && j+ele<=k) {
                    temp[j+ele]=true;
                }
            }
            for(int j=0;j<=k;j++) {
                dp[i][j]=dp[i-1][j]|temp[j];
            }
        }

        for(int i=1;i<=n;i++){
            int ub = getUpperBound(i,nums);
            int cnt = n-ub;
            int total=cnt*i;
            boolean flag=false;
            if(ub==0){
                if(k%i==0 && (k/i)<=cnt) flag=true;
            }
            else{
                for(int j=0;j<=k;j++) {
                    if(dp[ub-1][j]) {
                        int rk = k-j;
                        if(rk%i==0 && (rk/i)<=cnt) flag=true;
                    }
                }
            }
            ans[i-1]=flag;
        }
        return ans;
    }
}