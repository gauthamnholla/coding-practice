class Solution {
    // Size of a smaller 3x3 box
    int n = 3;
    // Total size of the board (9x9)
    int N = n * n;

    // Track how many times each number appears in each row, column, and box
    int[][] rows = new int[N][N + 1];
    int[][] columns = new int[N][N + 1];
    int[][] boxes = new int[N][N + 1];

    // The Sudoku board
    char[][] board;
    // Flag to stop once the puzzle is solved
    boolean sudokuSolved = false;

    // Check if a number can be placed at (row, col)
    public boolean couldPlace(int d, int row, int col) {
        int idx = (row / n) * n + col / n; // Box index
        return rows[row][d] + columns[col][d] + boxes[idx][d] == 0;
    }

    // Place a number d at (row, col)
    public void placeNumber(int d, int row, int col) {
        int idx = (row / n) * n + col / n;
        rows[row][d]++;
        columns[col][d]++;
        boxes[idx][d]++;
        board[row][col] = (char)(d + '0'); // Convert int to char
    }

    // Remove a number d from (row, col) → backtracking
    public void removeNumber(int d, int row, int col) {
        int idx = (row / n) * n + col / n;
        rows[row][d]--;
        columns[col][d]--;
        boxes[idx][d]--;
        board[row][col] = '.'; // Reset cell
    }

    // Move to the next cell
    public void placeNextNumbers(int row, int col) {
        if (row == N - 1 && col == N - 1) 
            sudokuSolved = true; // Reached end → solved
        else if (col == N - 1) 
            backtrack(row + 1, 0); // Move to next row
        else 
            backtrack(row, col + 1); // Move to next column
    }

    // Backtracking: try placing numbers in current cell
    public void backtrack(int row, int col) {
        if (board[row][col] == '.') { // Empty cell
            for (int d = 1; d <= 9; d++) { // Try digits 1–9
                if (couldPlace(d, row, col)) {
                    placeNumber(d, row, col);
                    placeNextNumbers(row, col);
                    if (!sudokuSolved) removeNumber(d, row, col); // Undo if not solved
                }
            }
        } else {
            // Cell already filled → move to next
            placeNextNumbers(row, col);
        }
    }

    // Solve the Sudoku board
    public void solveSudoku(char[][] board) {
        this.board = board;

        // Step 1: Initialize tracking arrays with given numbers
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (board[i][j] != '.') 
                    placeNumber(Character.getNumericValue(board[i][j]), i, j);

        // Step 2: Start backtracking from (0,0)
        backtrack(0, 0);
    }
}
