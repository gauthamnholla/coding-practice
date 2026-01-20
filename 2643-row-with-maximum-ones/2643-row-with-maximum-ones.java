class Solution {
    public int[] rowAndMaximumOnes(int[][] mat) {
        int  maxCnt=0;
       int index=0;
       for(int i=0;i<mat.length;i++){
        int cnt=0;
        for(int j=0;j<mat[0].length;j++){
            if(mat[i][j]==1) cnt+=1;
        }
        if(maxCnt<cnt) {
            maxCnt=cnt;
            index=i;
        }
       }
        return new int[]{index, maxCnt};
    }
}