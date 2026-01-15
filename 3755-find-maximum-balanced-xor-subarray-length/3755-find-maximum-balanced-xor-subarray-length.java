class Solution {
    public int maxBalancedSubarray(int[] nums) {
        Map<String, Integer> map = new HashMap<>();
        map.put("0.0", -1);    
        int pref = 0, bal = 0, max = 0;

        for(int i = 0; i < nums.length; ++i) {
            pref ^= nums[i];

            if(nums[i] % 2 == 0) ++bal;
            else --bal;

            String key = pref + "." + bal;
            if(map.containsKey(key)) {
                int prevIdx = map.get(key);
                max = Math.max(max, i - prevIdx);
            } 
            else map.put(key, i);
        }
        return max;
    }
}