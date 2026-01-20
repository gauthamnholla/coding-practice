import java.util.*;

class NeighborSum {
    private int[][] grid;
    private int length;
    private Map<Integer, int[]> positions;

    public NeighborSum(int[][] grid) {
        this.grid = grid;
        this.length = grid.length;
        this.positions = new HashMap<>();

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                positions.put(grid[i][j], new int[]{i, j});
            }
        }
    }

    public int adjacentSum(int value) {
        int[] pos = positions.get(value);
        int i = pos[0], j = pos[1];
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int result = 0;

        for (int[] d : dirs) {
            int x = i + d[0], y = j + d[1];
            if (x >= 0 && x < length && y >= 0 && y < length) {
                result += grid[x][y];
            }
        }
        return result;
    }

    public int diagonalSum(int value) {
        int[] pos = positions.get(value);
        int i = pos[0], j = pos[1];
        int[][] dirs = {{-1, -1}, {1, 1}, {-1, 1}, {1, -1}};
        int result = 0;

        for (int[] d : dirs) {
            int x = i + d[0], y = j + d[1];
            if (x >= 0 && x < length && y >= 0 && y < length) {
                result += grid[x][y];
            }
        }
        return result;
    }
}