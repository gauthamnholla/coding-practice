class Solution {
    public int[] searchRange(int[] nums, int target) {
       int si = lb(nums, target); //find starting index by calling lower bound
//if starting index is -1, means we won't get last index also, so directly return -1
       if(si==-1) return new int[]{-1,-1}; 
       int li = ub(nums,target); // find last index by calling upper bound
        return new int[]{si, li};
    }
//Method for lower bound - if we got index == target, we will search in left for more smaller index of target
    public int lb(int[] nums, int target){
        int si = -1;
        int low = 0, high = nums.length - 1;
        while(low<=high){
            int mid = (low+high)/2;
            if(nums[mid]==target){
                si = mid;
                high = mid-1;
            }
            else if(nums[mid]<target) low = mid+1;
            else high = mid-1;
        }
        return si;
    }
//Method for upper bound - if we got index == target, we will search in right for more larger index of target
     public int ub(int[] nums, int target){
        int ei = -1;
        int low = 0, high = nums.length - 1;
        while(low<=high){
            int mid = (low+high)/2;
            if(nums[mid]==target){
                ei = mid;
                low = mid+1;
            }
            else if(nums[mid]<target) low = mid+1;
            else high = mid-1;
        }
        return ei;
    }
}