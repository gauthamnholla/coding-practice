class Solution {
    public int commonFactors(int a, int b) {
        int gcd=gcd(a,b);
        int count=0;
        for(int i=1;i<=gcd;i++){
            if(gcd%i==0)count++;
        }
        return count;
    }
    public static int gcd(int a,int b){
        if(b==0)return a;
        return gcd(b,a%b);
    }
}