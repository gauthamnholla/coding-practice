class Solution {
        public int minimumArrayLength(int[] A) {
        int v = A[0], cnt = 0;
        for (int a: A)
            v = Math.min(v, a); 
        for (int x : A)
            if (x % v > 0)
                return 1;
        for (int a : A)
            if (a == v)
                cnt++;
        return (cnt + 1) / 2;
    }
}