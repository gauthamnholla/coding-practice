class Solution {
    private boolean isdividing(int i)
    {
        int copy=i;
        while(i>0)
        {
            int r=i%10;
            if(r==0)
            {
                return false;
            }
            if(r!=0 && copy%r!=0)
            {
                return false;
            }
            i=i/10;
        }
        return true;
    }
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> ans =new ArrayList<>();
        for(int i=left;i<=right;i++)
        {
            if(isdividing(i))
            {
                ans.add(i);
            }
        }
        return ans;
    }
}