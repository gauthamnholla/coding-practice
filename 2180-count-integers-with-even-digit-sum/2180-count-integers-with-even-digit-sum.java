class Solution {
    public int countEven(int num) {
        int count=0;
        int sum=0;
        for(int i=1;i<=num;i++)
        {
            if(i%2==0 && i<10)
            {
                count++;
            }
            else
            {
                sum=0;
                int temp=i;
                while(temp>0)
                {
                    int x=temp%10;
                    sum=sum+x;
                    temp=temp/10;
                }
                if(sum%2==0)
                {
                    count++;
                }
            }
        }
        return count;
    }
}