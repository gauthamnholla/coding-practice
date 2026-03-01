class Solution {
    int[][][] all = new int[][][]{
        {{0, 0}, {1, 0}, {-1, 0}, {0, -1}, {0, 1}}, // Rook
        {{0, 0}, {1, -1}, {1, 1}, {-1, 1}, {-1, -1}}, // Bishop
        {{0, 0}, {1, 0}, {-1, 0}, {0, -1}, {0, 1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}} // queen
    };
    int ROOK   = 0;
    int BISHOP = 1;
    int QUEEN  = 2;
    int[] type; // what type does piece[i] has?
    int[][] A; // our current position for each piece
    public int countCombinations(String[] pieces, int[][] positions) {
        A = positions;
        type = new int[pieces.length];
        for (int[] a : A){ // make it 0 index first
            a[0]--; a[1]--;
        }
        for (int i = 0; i < pieces.length; i++){ // assign the type for each piece
            type[i] = switch(pieces[i]){
                case "rook"   : yield ROOK;
                case "bishop" : yield BISHOP;
                default       : yield QUEEN;
            };
        }
        return choose(0, new int[A.length], new int[A.length][2]); // choose all destinations and return the sum of it
    }

    private int choose(int idx, int[] dir, int[][] dest){
        if (idx == A.length){ // now solve for this destination
            return solve(0, dir, dest);
        }
        int ans = 0;
        for (int i = 0; i < all[type[idx]].length; i++){ // first we choose direction
            int[] d = all[type[idx]][i];
            int nx = A[idx][0] + d[0], ny = A[idx][1] + d[1];
            dir[idx] = i;
            while(nx >= 0 && nx < 8 && ny >= 0 && ny < 8){ // then we move the destination along that direction for each piece
                dest[idx][0] = nx; // we can add a check to make sure no dup dest, but it is not really faster.
                dest[idx][1] = ny;
                ans += choose(idx+1, dir, dest);
                if (i == 0){ // i = 0 -> needs to break because it doesn't move
                    break;
                }
                nx += d[0];
                ny += d[1];
            }
        }
        return ans;
    }

    private int solve(int idx, int[] dir, int[][] dest){
        if (idx == A.length){ // at the end of 1 second.
            boolean ok = true;
            for (int i = 0; i < A.length && ok; i++){ // check if all pieces have reached the dest
                ok &= A[i][0] == dest[i][0] && A[i][1] == dest[i][1];
            }
            return ok? 1 : solve(0, dir, dest); // if yes, return 1, if not, go for the next second.
        }
        int j = dest[idx][0] == A[idx][0] && dest[idx][1] == A[idx][1]? 0 : dir[idx]; // changed dir to 0 if it is at the destination, so it doesn't move
        int[] d = all[type[idx]][j];
        A[idx][0] += d[0];
        A[idx][1] += d[1];
        boolean bad = false;
        for (int i = 0; i < idx && !bad; i++){ // check if it collides with any previous pieces
            bad |= A[idx][0] == A[i][0] && A[idx][1] == A[i][1];
        }
        int ans = bad? 0 : solve(idx+1, dir, dest); // if it does, return 0, if not, solve for the next piece.
        A[idx][0] -= d[0];
        A[idx][1] -= d[1];
        return ans;
    }
}