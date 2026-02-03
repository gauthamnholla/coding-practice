class Solution {
    private boolean isOk(int num ,int val){
        
        int sum=0;
        int num2=num;
        while(num>0){
            sum*=10;
            sum+=num%10;
            num/=10;
        }
        return ((sum+num2)==val);
    }
    public boolean sumOfNumberAndReverse(int num) {
        for(int i=0;i<=num;i++){
            if(isOk(i,num)){
                return true;
            }
        }
        return false;
    }
}