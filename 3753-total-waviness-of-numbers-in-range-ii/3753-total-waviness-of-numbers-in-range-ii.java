class Solution {
    Long  dp[][][][][][];
    public long totalWaviness(long num1, long num2) {
        dp=new Long[16][2][2][11][11][16];
        long c1=f(0,1,0,-1,-1,0,Long.toString(num1-1));
        dp=new Long[16][2][2][11][11][16];
    
        long c2=f(0,1,0,-1,-1,0,Long.toString(num2));
        return c2-c1;
    }
    public long f(int index,int tight,int validnum,int last2,int last1,int sc,String s){
        if(index==s.length()){
            if(validnum==1){
                return sc;
            }
            return 0;
        }
        if(dp[index][tight][validnum][last2+1][last1+1][sc]!=null){
            return dp[index][tight][validnum][last2+1][last1+1][sc];
            
        }
        int dig=s.charAt(index)-'0';
        int limit=tight==1?s.charAt(index)-'0':9;
        long ans=0;
        for(int d=0;d<=limit;d++){
            int newtight=(tight==1 && d==dig)?1:0;
            if(validnum==0 && d==0){
                ans+=f(index+1,newtight,0,-1,-1,0,s);
            }else if(validnum==0 && d>0){
                ans+=f(index+1,newtight,1,-1,d,0,s);
            }else if(last2==-1){
                ans+=f(index+1,newtight,1,last1,d,sc,s);
            }else{
                int score=((last1>d && last1>last2) || (last1<d && last1<last2))?1:0;
                ans+=f(index+1,newtight,1,last1,d,sc+score,s);
            }
        }
        
        
        return dp[index][tight][validnum][last2+1][last1+1][sc]=ans;
    }

}