class Solution {
    public int smallestValue(int n) {
   while(true) {
            int s=sum(n);
            if(s==n)
            break;
            n=s;
        }
        return n;

    }
    int sum(int x) {
        int sum = 0;
        for (int i = 2; i*i <= x; ) {
            if (x % i == 0) {
                sum += i;
                x /= i;
                i = 2;
            } else
                i++;
        }
        if (x > 1) {
            sum += x;
        }
        return sum;
    }
}