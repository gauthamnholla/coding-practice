class Solution {
    public int diagonalSum(int[][] mat) {
     int n=mat.length;
     if(n == 1){
        return mat[0][0];
     }
     int result=0;
     for(int i=0; i<n; i++){
        result +=mat[i][i];
     }
     int place=mat[0].length - 1;
     for(int i=0; i<n; i++){
        if(i == place) {
            place--;
            continue;
        }
        result +=mat[i][place];
        place--;
     }
     return result;
    }
}