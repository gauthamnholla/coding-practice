class Solution {
    public int minFlips(int[][] grid) {
        int rowcount = 0;
        for(int i = 0; i < grid.length; i++) {
            int start = 0, end = grid[i].length - 1;

            while(start < end) {
                if(grid[i][start] != grid[i][end]) rowcount++;
                start++; end--;
            }
        }

        int columncount = 0;
        for(int i = 0; i < grid[0].length; i++) {
            int start = 0, end = grid.length - 1;

            while(start < end) {
                if(grid[start][i] != grid[end][i]) columncount++;
                start++; end--;
            }
        }

        if(rowcount > columncount) return columncount;
        else return rowcount;
    }
}