class Solution {
    HashMap<String,Integer> memo;
    int dfs(int[] arr,int left,int n)
    {
        int q=0;
        String key=Arrays.toString(arr);
        key+=Integer.toString(left);
        if(memo.containsKey(key))
            return memo.get(key);
        else if(left==0)
        {
            for(int i=1;i<n;++i)
                if(arr[i]>0)
                {
                    arr[i]--;
                    q=Math.max(q,1+dfs(arr,n-i,n));
                    arr[i]++;
                }
        }
        else
            for(int i=1;i<n;++i)
                if(arr[i]>0)
                {
                    arr[i]--;
                    q=Math.max(q,dfs(arr,i<=left?left-i:n+left-i,n));
                    arr[i]++;
                }
        memo.put(key,q);
        return q;
        
    }
    public int maxHappyGroups(int n, int[] groups) 
    {
        int arr[]=new int[n];
        for(int x:groups)
            arr[x%n]++;
        memo=new HashMap<String,Integer>();
        return arr[0]+dfs(arr,0,n);
    }
}