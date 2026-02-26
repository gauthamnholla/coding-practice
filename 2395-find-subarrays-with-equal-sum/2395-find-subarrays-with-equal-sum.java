import java.util.*;

class Solution {
    public boolean findSubarrays(int[] nums) {
        if(nums.length < 2) return false;
        
        Map<Integer, Integer> freq = new HashMap<>();
        
        for(int i = 0; i < nums.length - 1; i++){
            int sum = nums[i] + nums[i + 1];
            if(freq.getOrDefault(sum, 0) > 0){
                return true;
            }
            freq.put(sum, freq.getOrDefault(sum, 0) + 1);
        }
        
        return false;
    }
}