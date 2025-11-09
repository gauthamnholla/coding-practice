class Solution {
    public int findLucky(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>(); // Store number → frequency

        // Count frequency of each number
        for (int n : arr) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        int luckyInteger = -1; // Default if no lucky number found

        // Check for numbers where value == frequency
        for (int key : map.keySet()) {
            if (map.get(key) == key) {
                luckyInteger = key; // Update lucky number
            }
        }

        return luckyInteger; // Return result (largest lucky number if multiple)
    }
}
