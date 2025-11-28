class Solution {
    public int kthLargestValue(int[][] M, int k) {

        /*
            We maintain a dp[i][j], which strores the xor value upto (i,j).
            Now for the i+1, row, we maintain a temp variable, which stores the xor upto
            jth element in that row.
            So now dp[i+1][j] = dp[i][j] ^ temp ;

            Then simply store each dp[i][j] value in a array, and return the Kth largest.
        */

        int m = M.length ;
        int n = M[0].length ;

        int[][] dp = new int[m][n] ;

        int len = m * n ;
        int[] res = new int[len] ;
        int idx = 0 ;

        int curr = 0 ;
        for(int c = 0 ; c < n ; c++)
        {
            curr ^= M[0][c] ;
            dp[0][c] = curr ;
            res[idx++] = curr ;
        }

        for(int r = 1 ; r < m ; r++)
        {
            int temp = 0 ;
            for(int c = 0 ; c < n ; c++)
            {
                temp ^= M[r][c] ;
                int val = temp^dp[r-1][c] ;
                dp[r][c] = val ;
                res[idx++] = val ;
            }
        }

        Arrays.sort(res) ;

        return res[len - k] ;
        
    }
}