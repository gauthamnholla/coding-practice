class MapSum {
    // Arrays to store keys and their corresponding values
    private String[] keys;
    private int[] values;
    
    // A set to check whether a key already exists
    private HashSet<String> uniqueKeys;
    
    // Counter to track total number of stored keys
    private int count;

    /** Initialize the MapSum data structure. */
    public MapSum() {
        keys = new String[1001];
        values = new int[1001];
        uniqueKeys = new HashSet<>();
        count = 0;
    }

    /**
     * Inserts a key-value pair into the structure.
     * If the key already exists, its value is updated.
     */
    public void insert(String key, int val) {
        // If key already exists, just update its value
        if (!uniqueKeys.add(key)) {
            for (int i = 0; i < count; i++) {
                if (keys[i].equals(key)) {
                    values[i] = val; // Update old value
                    return;
                }
            }
        }
        
        // Otherwise, add a new key-value pair
        keys[count] = key;
        values[count++] = val;
    }

    /**
     * Returns the sum of all values of keys that start with the given prefix.
     */
    public int sum(String prefix) {
        int total = 0;
        
        // Traverse all keys and accumulate values of matching prefixes
        for (int i = 0; i < count; i++) {
            if (keys[i].startsWith(prefix)) {
                total += values[i];
            }
        }
        
        return total;
    }
}

/**
 * Example Usage:
 * MapSum obj = new MapSum();
 * obj.insert("apple", 3);
 * obj.insert("app", 2);
 * System.out.println(obj.sum("ap"));  // Output: 5
 */


/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */