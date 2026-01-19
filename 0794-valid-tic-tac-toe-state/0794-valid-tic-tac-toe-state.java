class Solution {
    public boolean validTicTacToe(String[] board) {
        int countX = 0, countO = 0;
        
        for (String row : board) {
            for (char cell : row.toCharArray()) {
                if (cell == 'X') countX++;
                if (cell == 'O') countO++;
            }
        }
        
        if (countO > countX || countX > countO + 1) return false;
        
        boolean xWins = isWinner(board, 'X');
        boolean oWins = isWinner(board, 'O');

        if (xWins && oWins) return false;
        if (xWins && countX != countO + 1) return false;
        if (oWins && countX != countO) return false;
        
        return true;
    }
    
    private boolean isWinner(String[] board, char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i].equals("" + player + player + player) || 
                (board[0].charAt(i) == player &&
                 board[1].charAt(i) == player &&
                 board[2].charAt(i) == player)) {
                return true;
            }
        }
        return (board[0].charAt(0) == player &&
                board[1].charAt(1) == player &&
                board[2].charAt(2) == player) ||
               (board[0].charAt(2) == player &&
                board[1].charAt(1) == player &&
                board[2].charAt(0) == player);
    }
}