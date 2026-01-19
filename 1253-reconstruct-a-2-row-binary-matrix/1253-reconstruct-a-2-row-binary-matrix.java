class Solution {
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        int n = colsum.length;
        List<List<Integer>> result = new ArrayList<>();
        Integer upperRow[] = new Integer[n];
        Integer lowerRow[] = new Integer[n];
        
        // Initialize both rows with zeros
        Arrays.fill(upperRow, 0);
        Arrays.fill(lowerRow, 0);
        
        // First pass: handle columns where colsum[i] is 2
        for (int i = 0; i < n; i++) {
            if (colsum[i] == 2) {
                // Both rows must have a 1 in the i-th column
                upperRow[i] = 1;
                lowerRow[i] = 1;
                upper--;
                lower--;
            }
        }
        
        // Second pass: handle columns where colsum[i] is 1
        for (int i = 0; i < n; i++) {
            if (colsum[i] == 1) {
                if (upper > 0) {
                    // Place 1 in the upper row if possible
                    upperRow[i] = 1;
                    upper--;
                } else if (lower > 0) {
                    // Otherwise, place 1 in the lower row
                    lowerRow[i] = 1;
                    lower--;
                } else {
                    // If neither row can accommodate the 1, return an empty list
                    return result;
                }
            }
        }
        
        // Check if all the required 1's are placed correctly
        if (upper != 0 || lower != 0) {
            return result;
        }
        
        // Convert arrays to lists and add to the result
        result.add(Arrays.asList(upperRow));
        result.add(Arrays.asList(lowerRow));
        return result;
    }
}