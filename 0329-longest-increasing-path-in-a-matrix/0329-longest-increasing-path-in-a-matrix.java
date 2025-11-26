class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        HashMap<Integer, ArrayList<int[]>> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        
        for(int i = 0;i < row;i++){
            for(int j = 0;j < col;j++){
                if(!map.containsKey(matrix[i][j])){
                    map.put(matrix[i][j], new ArrayList<>());
                    list.add(matrix[i][j]);
                }
                map.get(matrix[i][j]).add(new int[]{i, j});
            }
        }
        
        int[][] high = new int[row][col];
        Collections.sort(list);
        Collections.reverse(list);
        for(int ele: list){
            for(int[] ind: map.get(ele)){
                int i = ind[0];
                int j = ind[1];
                
                if(i-1 >= 0 && matrix[i][j] < matrix[i-1][j]){
                    high[i][j] = Math.max(high[i][j], high[i-1][j]+1);
                }
                if(i+1 < row && matrix[i][j] < matrix[i+1][j]){
                    high[i][j] = Math.max(high[i][j], high[i+1][j]+1);
                }
                if(j-1 >= 0 && matrix[i][j] < matrix[i][j-1]){
                    high[i][j] = Math.max(high[i][j], high[i][j-1]+1);
                }
                if(j+1 < col && matrix[i][j] < matrix[i][j+1]){
                    high[i][j] = Math.max(high[i][j], high[i][j+1]+1);
                }
                high[i][j] = Math.max(high[i][j], 1);
            }
        }
        
        int ans = 0;
        for(int[] val: high){
            for(int ele: val) ans = Math.max(ans, ele);
        }
        return ans;
    }
}