class Solution {
    public int[] occurrencesOfElement(int[] nums, int[] queries, int x) {
        // Map to store occurrence count and corresponding index
        HashMap<Integer, Integer> mp = new HashMap<>();
        int count = 0;  // Counter for occurrences of x
        int n = nums.length;  // Size of the nums array

        // Traverse the nums array to count occurrences of x
        for (int i = 0; i < n; i++) {
            if (nums[i] == x) {
                count++;  // Increment count when x is found
                mp.put(count, i);  // Map the current count to the index i
            }
        }

        // Process each query to find the index of the queries[i]th occurrence of x
        for (int i = 0; i < queries.length; i++) {
            if (mp.containsKey(queries[i])) {
                // If the occurrence exists in the map, update queries[i] with the index
                queries[i] = mp.get(queries[i]);
            } else {
                // If the occurrence does not exist, update queries[i] with -1
                queries[i] = -1;
            }
        }

        // Return the modified queries array containing the results
        return queries;
    }
}