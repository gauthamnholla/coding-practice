class Solution {
        private static final int[][] d = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
    public boolean checkMove(char[][] board, int r, int c, char color) {
        for (int k = 0; k < 8; ++k) {
            for (int i = r + d[k][0], j = c + d[k][1], size = 2; 0 <= i && i < 8 && 0 <= j && j < 8; i += d[k][0], j += d[k][1], ++size) {
                if (board[i][j] == '.' || size < 3 && board[i][j] == color) {
                    break;
                } 
                if (board[i][j] == color) {
                    return true;
                }
            }
        }
        return false;
    }
}