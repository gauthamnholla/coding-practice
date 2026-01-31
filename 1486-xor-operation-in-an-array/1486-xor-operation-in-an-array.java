class Solution {
    public  int rec(int n, int s, int sum)
    {
        if(n == 0)
            return sum;
        return rec(n - 1, s, sum ^ (s + 2 * n));
    }
    public int xorOperation(int n, int start) {
        return rec(n - 1, start, start + 2 * 0);
    }
}