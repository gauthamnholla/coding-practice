class Solution {
    public int minMoves(int target, int maxDoubles) {
        int t = target;
        int moves = 0;

        while (t != 1) {
            if (maxDoubles == 0) {
                // Only option is to decrement all the way to 1
                moves += t - 1;
                t = 1;
            } else {
                if (t % 2 == 0) {
                    // Use a double in reverse (divide by 2)
                    t /= 2;
                    maxDoubles--;
                } else {
                    // Make the number even
                    t--;
                }
                moves++;
            }
        }
        return moves;
    }
}