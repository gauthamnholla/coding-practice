class Solution {
    public int dfs(char[][] board  , int x , int y) {
        int cnt = 0;
        for (int i = x - 1; i >= 0; i--) {
            if (board[i][y] == 'p') {
                cnt++;
                break;
            }
            if (board[i][y] == 'B') break;
        }
        for (int i = x + 1; i < 8; i++) {
            if (board[i][y] == 'p') {
                cnt++;
                break;
            }
            if (board[i][y] == 'B') break;
        }
        for (int j = y - 1; j >= 0; j--) {
            if (board[x][j] == 'p') {
                cnt++;
                break;
            }
            if (board[x][j] == 'B') break;
        }
        for (int j = y + 1; j < 8; j++) {
            if (board[x][j] == 'p') {
                cnt++;
                break;
            }
            if (board[x][j] == 'B') break;
        }
        return cnt;
    }

    public int numRookCaptures(char[][] board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 'R') {
                    return dfs(board , i , j);
                }
            }
        }
        return 0;
    }
}