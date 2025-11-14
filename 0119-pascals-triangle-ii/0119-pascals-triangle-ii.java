class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        row.add(1); // First row always [1]

        // Build each row up to the target rowIndex
        for (int i = 0; i < rowIndex; i++) {
            List<Integer> newRow = new ArrayList<>();
            newRow.add(1); // First element is always 1

            // Each middle element = sum of two elements above it
            for (int j = 1; j < row.size(); j++) {
                newRow.add(row.get(j - 1) + row.get(j));
            }

            newRow.add(1); // Last element is always 1
            row = newRow;  // Move to next row
        }

        return row; // Final row
    }
}

