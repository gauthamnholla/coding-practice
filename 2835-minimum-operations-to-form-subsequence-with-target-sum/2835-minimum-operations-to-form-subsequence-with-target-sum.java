class Solution {
        public int minOperations(List<Integer> A, int target) {
        long tot = 0;
        for (int a : A) {
            tot += a;
        }
        if (tot < target) {
            return -1;
        }
        Collections.sort(A);
        int res = 0;
        while (target > 0) {
            int a = A.remove(A.size() - 1);
            if (tot - a >= target) {
                tot -= a;
            } else if (a <= target) {
                tot -= a;
                target -= a;
            } else {
                A.add(a / 2);
                A.add(a / 2);
                res++;
            }
        }
        return res;
    }
}