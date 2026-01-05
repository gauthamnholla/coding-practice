class FenwickTree {
    private int[] tree;

    public FenwickTree(int size) {
        tree = new int[size + 1];
    }

    public void add(int i, int delta) {
        while (i < tree.length) {
            tree[i] += delta;
            i += i & -i;
        }
    }

    public int query(int i) {
        int s = 0;
        while (i > 0) {
            s += tree[i];
            i -= i & -i;
        }
        return s;
    }
}

class Solution {
    public int[] minDeletions(String s, int[][] queries) {
        int n = s.length();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = s.charAt(i) - 'A';
        }

        FenwickTree bit = new FenwickTree(n);
        for (int i = 0; i < n - 1; i++) {
            if (A[i] == A[i + 1]) {
                bit.add(i + 1, 1);
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int[] q : queries) {
            if (q[0] == 1) {
                int i = q[1];
                A[i] ^= 1;
                if (i > 0) {
                    bit.add(i, A[i] == A[i - 1] ? 1 : -1);
                }
                if (i < n - 1) {
                    bit.add(i + 1, A[i] == A[i + 1] ? 1 : -1);
                }
            } else {
                res.add(bit.query(q[2]) - bit.query(q[1]));
            }
        }

        int[] res2 = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            res2[i] = res.get(i);
        }
        return res2;
    }
}