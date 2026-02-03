class Solution {
    public int countDigits(int num) 
    {
        int tar=num;
        int cnt=0;
        while(num>0)
        {
            int dig=num%10;
            if(tar%dig==0) cnt++;
            num=num/10;
        }
        return cnt;
    }
}