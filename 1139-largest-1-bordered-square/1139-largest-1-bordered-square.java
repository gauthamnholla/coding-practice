
class Solution {
    public int largest1BorderedSquare(int[][] grid) {
        // corner case
        if (grid.length == 0 || grid[0].length == 0) return 0;

        // build 2 DPs
        int[][] rightDP, downDP;
        int[][][] dps;

        dps = buildRightDownDPs(grid);
        rightDP = dps[0]; downDP = dps[1];

        // construct result
        int result = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // find the max valid square_edge_len for current TopLeft Corner
                for (
                    int edgeLen = Math.min(rightDP[i][j], downDP[i][j]); 
                    edgeLen >= 1; edgeLen--
                ) {
                    // current topRight corner is:   {i,         j + k - 1}
                    // current bottomLeft corner is: {i + k - 1, j        }
                    if (
                        downDP[i][j + edgeLen - 1] >= edgeLen &&
                        rightDP[i + edgeLen - 1][j] >= edgeLen
                    ) {
                        // update result
                        result = Math.max(result, edgeLen); // store maxEdgeLen, NOT area
                        break;
                    }
                }
            }
        }

        return result * result;
    }

    private int[][][] buildRightDownDPs(int[][] arr) {
        int[][] rightDP = new int[arr.length][arr[0].length];
        int[][] downDP = new int[arr.length][arr[0].length];

        int rows = arr.length;
        int cols = arr[0].length;

        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 0; j--) {
                // fill rightDP
                rightDP[i][j] = (arr[i][j] == 1) ? 1 : 0;
                if (!(arr[i][j] == 0 || j == cols - 1))
                    rightDP[i][j] += rightDP[i][j + 1];

                // fill downDP
                downDP[i][j] = (arr[i][j] == 1) ? 1 : 0;
                if (!(arr[i][j] == 0 || i == rows - 1))
                    downDP[i][j] += downDP[i + 1][j];
            }
        }

        return new int[][][]{rightDP, downDP};
    }
}