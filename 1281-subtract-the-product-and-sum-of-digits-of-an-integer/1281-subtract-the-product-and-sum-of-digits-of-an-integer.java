class Solution {
    public int subtractProductAndSum(int n) {
        int p=1,s=0,k=n;
        while(k!=0){
            int r=k%10;
            p*=r;
            s+=r;
            k/=10;
        }
        return p-s;
    }
}