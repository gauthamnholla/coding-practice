class Solution {
    public int alternateDigitSum(int n) {
        int rem=0;
        int sum=0;
        
        while(n>0){
            rem=n%10;
            sum=sum*10+rem;
            n=n/10;
        }
        int rem2=0;
        int sum2=0;
        int c=1;
        while(sum>0){
            rem2=sum%10;
            if(c%2 == 0){
                sum2=sum2-rem2;
            }else{
                sum2=sum2+rem2;
            }
            c++;
            
            
            sum=sum/10;
        }
        return sum2;
    }
}