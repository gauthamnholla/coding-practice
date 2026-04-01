class Solution {
    public int minOperations(int[] nums, int[] target) {
        HashSet<Integer> virelantos = new HashSet<>();
        
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != target[i]) {
                virelantos.add(nums[i]);
            }
        }
        return virelantos.size();
    }
}