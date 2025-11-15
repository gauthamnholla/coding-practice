class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>(); // Store restaurant → index from list1
        List<String> res = new ArrayList<>();
        int min = Integer.MAX_VALUE; // Track smallest index sum

        // Step 1: Map all restaurants in list1 with their indices
        for (int i = 0; i < list1.length; i++)
            map.put(list1[i], i);

        // Step 2: Go through list2 and find common restaurants
        for (int j = 0; j < list2.length; j++) {
            if (map.containsKey(list2[j])) {
                int sum = j + map.get(list2[j]); // Calculate index sum

                // Found a smaller index sum → clear old results
                if (sum < min) {
                    res.clear();
                    res.add(list2[j]);
                    min = sum;
                }
                // Same min sum → add to results
                else if (sum == min) {
                    res.add(list2[j]);
                }
            }
        }

        // Step 3: Convert list to array
        return res.toArray(new String[res.size()]);
    }
}
