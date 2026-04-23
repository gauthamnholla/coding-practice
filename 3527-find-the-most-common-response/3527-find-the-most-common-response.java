class Solution {
    public String findCommonResponse(List<List<String>> responses) {
        
        // Map to store how many days each response appears in
        Map<String, Integer> map = new HashMap<>();

        // Go through each day's list of responses
        for(List<String> day :responses)
        {

            // Use a Set to remove duplicate responses from the same day
            Set<String> set = new HashSet<>();
            for(String res : day)
            {
                set.add(res);
            }
            // For each unique response in this day
            for(String op : set)
            {
                // Increase its count by 1 (means it appeared on one more day)
                map.put(op,map.getOrDefault(op,0)+1);
            }
        }
    
    String ans = "";
    int max = 0;

    // Go through all responses in the map
    for(String key : map.keySet())
    {
        // If this response has higher count OR
        // same count but comes earlier alphabetically
        int freq = map.get(key);
        if(freq>max || (freq == max && key.compareTo(ans)<0))
        {
            max = freq;
            ans = key;
        }
    }
    
    return ans;
    }
}