import java.util.*;

class Solution {

    int count(int[] arr, int target, int i, List<Map<Integer,Integer>> dp){
        if(i < 0){
            return target == 0 ? 0 : -1;
        }

        if(dp.get(i).containsKey(target)){
            return dp.get(i).get(target);
        }

        int take = -1;
        int temp = count(arr, target ^ arr[i], i-1, dp);
        if(temp != -1){
            take = 1 + temp;
        }

        int notTake = count(arr, target, i-1, dp);

        int res = Math.max(take, notTake);
        dp.get(i).put(target, res);
        return res;
    }

    public int minRemovals(int[] nums, int target) {
        int n = nums.length;
        List<Map<Integer,Integer>> dp = new ArrayList<>();

        for(int i=0;i<n;i++){
            dp.add(new HashMap<>());
        }

        int temp = count(nums, target, n-1, dp);
        if(temp == -1) return -1;
        return n - temp;
    }
}