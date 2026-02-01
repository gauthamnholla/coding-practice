class Solution {
    public boolean isThree(int n) {
        int divisor = 0;
        for(int i = 1; i <= n; i++){
            if(n % i == 0){
                divisor++;
            }
        }

        return (divisor == 3) ? true : false;
    }
}