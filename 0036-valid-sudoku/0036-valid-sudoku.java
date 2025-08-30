class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean isValid = true;

        // Lists of HashSets to track numbers seen in rows, columns, and boxes
        List<HashSet<Integer>> listRow = new ArrayList<>();
        List<HashSet<Integer>> listCol = new ArrayList<>();
        List<HashSet<Integer>> listBox = new ArrayList<>();

        int boxNumber = 0;

        // Initialize 9 HashSets for rows, columns, and boxes
        for(int i = 0; i < 9; i++){
            HashSet<Integer> row = new HashSet<>();
            HashSet<Integer> col = new HashSet<>();
            HashSet<Integer> box = new HashSet<>();
            listRow.add(row);
            listCol.add(col);
            listBox.add(box);
        }

        // Traverse the board
        for(int row = 0; row < 9 && isValid; row++){
            for(int col = 0; col < 9 && isValid; col++){
                if(board[row][col] != '.') {  // Only check filled cells
                    boxNumber = getBoxNumber(row, col); // Find which 3x3 box this cell belongs to
                    int value = board[row][col] - '0';  // Convert char to integer

                    // Check for duplicates in the current row
                    if (listRow.get(row).contains(value)) {
                        isValid = false;
                    } else {
                        listRow.get(row).add(value);
                    }

                    // Check for duplicates in the current column
                    if (listCol.get(col).contains(value)) {
                        isValid = false;
                    } else {
                        listCol.get(col).add(value);
                    }

                    // Check for duplicates in the current 3x3 box
                    if (listBox.get(boxNumber).contains(value)) {
                        isValid = false;
                    } else {
                        listBox.get(boxNumber).add(value);
                    }
                }
            }
        }

        // Return true if no duplicates found, otherwise false
        return isValid;
    }

    // Helper method to calculate 3x3 box index from row and column
    private static int getBoxNumber(int row, int col) {
        row = row / 3;   // Normalize row index to box index
        col = col / 3;   // Normalize col index to box index
        return row * 3 + col; // Unique box number from 0–8
    }
}
