class Solution {
    public int sumOfTheDigitsOfHarshadNumber(int x) {
        int temp = x,sum = 0;
        while(x>0){
            sum = sum + x%10;
            x /= 10;
        }
        if(temp%sum == 0){
            return sum;
        }
        return -1;
    }
}