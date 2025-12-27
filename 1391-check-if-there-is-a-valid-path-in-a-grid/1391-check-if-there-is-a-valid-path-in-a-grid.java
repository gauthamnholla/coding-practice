class Solution {
    
    int n , m;
    Map<Integer, int[]> map;
    int dir[];
    public boolean hasValidPath(int[][] grid) {
        n = grid.length; m = grid[0].length;
        boolean visited[][] = new boolean[n][m];
        dir = new int []{0, 1, 0, -1, 0};
        map = new HashMap<>();
        map.put(1, new int[]{2, 0});
        map.put(2, new int[]{3, 1});
        map.put(3, new int[]{2, 1});
        map.put(4, new int[]{0, 1});
        map.put(5, new int[]{2, 3});
        map.put(6, new int[]{0, 3}); 
        return dfs(grid, visited, 0, 0);
    }
    public boolean dfs(int [][]grid, boolean[][] visited, int i, int j){
        System.out.println(" i = " + i + " j = "+j);
        if(i == n-1 && j == m-1) return true;
        if(!visited[i][j]){
            visited[i][j] = true;
            int[] direction = map.get(grid[i][j]);
            boolean firstDirection = false;
            int nr = i + dir[direction[0]], nc = j + dir[direction[0]+1];
            if(nr >= 0 && nc >= 0 && nr < n && nc < m && canGoBack(grid, i, j, nr, nc)){
                firstDirection = dfs(grid, visited, nr, nc);
            }
            boolean secondDirection =  false;
            nr = i + dir[direction[1]]; nc = j + dir[direction[1]+1];
            if(nr >= 0 && nc >= 0 && nr < n && nc < m && canGoBack(grid, i, j, nr, nc)){
                secondDirection = dfs(grid, visited, nr, nc);
            } 
            return firstDirection || secondDirection;
        }else return false;  
    }
    public boolean canGoBack(int [][]grid, int i , int j, int nr, int nc){
        int[]direction = map.get(grid[nr][nc]);
        if(nr + dir[direction[0]] == i && nc + dir[direction[0]+1] == j) return true;
        else if(nr + dir[direction[1]] == i && nc + dir[direction[1] + 1] == j)return true;
        else return false;
    }
}