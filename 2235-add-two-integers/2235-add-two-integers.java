class Solution {
    public int sum(int num1, int num2) {
        int[] dif = new int[4];
        List<range> add = new ArrayList<>(Arrays.asList(new range(1, 2, num1), new range(1, 2, num2)));
        for (range op : add) {
            dif[op.l] += op.v;
            dif[op.r+1] -= op.v;
        }
        for (int i = 1; i < 4; i++) {
            dif[i] += dif[i-1];
        }
        return dif[2];
    }
    public class range {
        int l, r, v;
        range(int lft, int rht, int val) {
            l = lft; r = rht; v = val;
        }
    }
}