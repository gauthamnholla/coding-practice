class Solution {
    public int minSwap(int[] nums1, int[] nums2) {

    int dp[] = new int[nums1.length];
    Arrays.fill(dp,-1);
    
    int ans = solve(nums1,nums2,0,dp);
    
    return ans;
}

public int solve(int nums1[],int nums2[],int ind,int[] dp){
    
    if(ind == nums1.length) return 0;
    
	// Condition 1
	
    if(ind>0 && (nums1[ind-1]>=nums1[ind] || nums2[ind-1]>=nums2[ind])) {
        
			//	nums1[] = [3,2]
			//	nums2[] = [1,4]
			
            int t = nums1[ind];
            nums1[ind] = nums2[ind];
            nums2[ind] = t;
            
           int val = 1+solve(nums1,nums2,ind+1,dp);
        
		// Since after swapping array becomes 
		// nums1[] = [3,4]
		// nums2[] = [1,2]
		// therefore we have to swap it back so that we can have our original array.
		
            t = nums1[ind];
            nums1[ind] = nums2[ind];
            nums2[ind] = t;
        
        return val;
        
    }  
	// Condition 2
	
	else if(ind>0 && (nums1[ind-1]>=nums2[ind] || nums2[ind-1]>=nums1[ind])) {
			return solve(nums1,nums2,ind+1,dp);
    }
	// Condition 3
	
    else {
        if(dp[ind] != -1) return dp[ind];
        
            int tempAns1 = solve(nums1,nums2,ind+1,dp);
    
            int t = nums1[ind];
            nums1[ind] = nums2[ind];
            nums2[ind] = t;
    
            int tempAns2 = 1+solve(nums1,nums2,ind+1,dp);
        
            t = nums1[ind];
            nums1[ind] = nums2[ind];
            nums2[ind] = t;
    
         return dp[ind] = Math.min(tempAns1,tempAns2);
    }
    
}
}