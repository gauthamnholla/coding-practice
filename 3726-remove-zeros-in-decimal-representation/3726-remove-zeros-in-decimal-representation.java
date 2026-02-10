class Solution {
    public long removeZeros(long n) {
        long ans = 0;
        long digi_place =1;

        while(n>0){
            long num = n%10;
            if(num != 0){
                ans = ans+num*digi_place;
                digi_place*=10;
            }
           n = n/10;
        }

        return ans;
    }
}