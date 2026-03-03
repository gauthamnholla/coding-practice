class Solution {
    public List<Integer> findLonely(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        
        // Count frequency of each number
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        List<Integer> list = new ArrayList<>();
        
        // Check lonely condition
        for (int num : nums) {
            if (map.get(num) == 1 &&
                !map.containsKey(num - 1) &&
                !map.containsKey(num + 1)) {
                list.add(num);
            }
        }
        
        return list;
    }
}