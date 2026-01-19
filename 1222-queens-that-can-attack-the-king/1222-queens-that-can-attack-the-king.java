class Solution {
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        List<List<Integer>> ans=new ArrayList<>();
        boolean[][] matrix=new boolean[8][8];
        for(int i[]: queens){
            matrix[i[0]][i[1]]=true;
        }

        int[][] dir={
            {-1,0},// up
            {1,0},// down
            {0,1}, // right
            {0,-1}, // left
            {-1,-1}, // top left
            {-1,1}, // top right
            {1,-1}, // dottom left
            {1,1} // bottom right
        };
        for(int d[]: dir){
            int dr=king[0];
            int dc=king[1];

            while(dr>=0 && dr<8 && dc>=0 && dc<8){
                dr+=d[0];
                dc+=d[1];

                if (dr < 0 || dr >= 8 || dc < 0 || dc >= 8) break;

                
                if(matrix[dr][dc]){
                    ans.add(Arrays.asList(dr,dc));
                    break;
                }
            }
        }
        return ans;
            
    }
}