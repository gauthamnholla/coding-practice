class Solution {

    Integer dp[][];
    public int minDistance(String word1, String word2) {

        int n=word1.length();
        int m=word2.length();

        dp=new Integer[n+1][m+1];

        int ans=(m+n) - 2*lcs(word1,word2,n,m);
        return ans;
    }

    int lcs(String s1,String s2,int i,int j)
    {
        if(i == 0 || j == 0) return 0;

        if(dp[i][j] != null) return dp[i][j];
        if(s1.charAt(i-1) == s2.charAt(j-1))
        {
            return dp[i][j]=1+lcs(s1,s2,i-1,j-1);
        }

        else
        {
            return dp[i][j]=Math.max(lcs(s1,s2,i-1,j),lcs(s1,s2,i,j-1));
        }
    }
}