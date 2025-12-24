class Solution {
    Map<String,Integer>dp=new HashMap<>();
    public int findRotateSteps(String ring, String key) {
        return ans(ring,0,key);
    }
    public int ans(String ring,int idx,String key)
    {
        int n=key.length();
        if(n==idx)return 0;

        String kk=idx+"-"+ring;

        if(dp.containsKey(kk))return dp.get(kk);

        int m=ring.length();
        int ll=Integer.MAX_VALUE;
        for(int i=0;i<m;i++)
        {
            char ch=ring.charAt(i);
            if(ch==key.charAt(idx))
            {
                String str1=ring.substring(0,i);
                String str2=ring.substring(i,m);
                ll=Math.min(ll,1+ans(str2+str1,idx+1,key)+i);
                break;
            }
        }
        int cnt=1;
        for(int i=m-1;i>=0;i--)
        {
            char ch=ring.charAt(i);
            if(ch==key.charAt(idx))
            {
                String str1=ring.substring(0,i);
                String str2=ring.substring(i,m);
                ll=Math.min(ll,1+ans(str2+str1,idx+1,key)+cnt);
                break;
            }
            cnt++;
        }
        dp.put(kk,ll);
        return ll;
    }
}