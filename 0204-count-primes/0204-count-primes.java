class Solution {
    int count (int n)
    {
        if(n==0||n==1)
        {
            return 0;
        }
        boolean[] is_prime=new boolean[n];
        Arrays.fill(is_prime,true);

        for(int i=2;i*i<n;i++)
        {
            if(is_prime[i])
            {
                for(int j=i*i;j<n;j=j+i)
                {
                    is_prime[j]=false;
                }
            }
        }
        int c=0;
        for(boolean x:is_prime)
        {
            if(x)
            {
                c++;
            }
        }
        return c-2;
    }
    public int countPrimes(int n) {
        return count(n);
    }
}